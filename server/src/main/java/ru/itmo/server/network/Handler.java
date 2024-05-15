package ru.itmo.server.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.general.managers.CommandManager;
import ru.itmo.general.network.Request;
import ru.itmo.general.network.Response;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * Handles incoming requests from clients on a separate thread.
 *
 * @author zevtos
 */
class Handler {
    private static final Logger logger = LoggerFactory.getLogger("Handler");
    private final SocketChannel clientSocketChannel;
    private final ByteArrayOutputStream byteArrayOutputStream;
    private final SelectionKey key;

    /**
     * Constructs a new Handler object.
     *
     * @param clientSocketChannel   The socket channel connected to the client.
     * @param byteArrayOutputStream The output stream containing the client's request.
     * @param key                   The selection key associated with the client's channel.
     */
    public Handler(
            SocketChannel clientSocketChannel,
            ByteArrayOutputStream byteArrayOutputStream,
            SelectionKey key) {
        this.clientSocketChannel = clientSocketChannel;
        this.byteArrayOutputStream = byteArrayOutputStream;
        this.key = key;
    }

    public void run() {
        try {
            byte[] requestBytes = byteArrayOutputStream.toByteArray();
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(requestBytes));
            Request request = (Request) objectInputStream.readObject();
            if ("exit".equals(request.getCommand())) {
                logger.info("Client {} terminated", clientSocketChannel.getRemoteAddress());
                clientSocketChannel.close();
                return;
            }


            handleRequest(request);

        } catch (Exception e) {
            logger.error("Error processing request: {}", e.getMessage());
            sendErrorResponse(clientSocketChannel);
        }
        // Set interest back to OP_READ after parsing is complete
        key.interestOps(key.interestOps() | SelectionKey.OP_READ);
        // Wake up the selector to update interest operations
        key.selector().wakeup();
    }

    /**
     * Handles the incoming request based on the user's authentication status.
     *
     * @param request The request object received from the client.
     */
    private void handleRequest(Request request) {
        Response response = CommandManager.handle(request);
        TCPWriter.sendResponse(clientSocketChannel, response);
    }

    /**
     * Sends an unauthorized response to the client, indicating that authentication is required.
     *
     * @param channel The socket channel to send the response to.
     */
    private void sendUnauthorizedResponse(SocketChannel channel) {
        Response response = new Response(false, "Вы не вошли в систему." + '\n' +
                "Введите register для регистрации или login для входа");
        TCPWriter.sendResponse(channel, response);
    }

    /**
     * Sends an error response to the client, indicating that the request was invalid.
     *
     * @param channel The socket channel to send the response to.
     */
    private void sendErrorResponse(SocketChannel channel) {
        Response response = new Response(false, "Invalid request");
        TCPWriter.sendResponse(channel, response);
    }
}