package ru.itmo.server.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itmo.general.managers.CommandManager;
import ru.itmo.server.colletions.SpaceMarineCollectionManager;
import ru.itmo.server.network.TCPServer;
import sun.misc.Signal;


/**
 * Главный класс приложения.
 *
 * @author zevtos
 */
public class Main {
    private static final int MISSING_FILE_ARGUMENT_EXIT_CODE = 1;
    private static final int PORT = 4092;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        setSignalProcessing("INT", "TERM", "TSTP", "BREAK", "EOF");




        var spCollectionManager = new SpaceMarineCollectionManager();

        CommandManager.initServerCommands(spCollectionManager);
        TCPServer tcpServer = new TCPServer(PORT);
        tcpServer.start();
    }

    /**
     * Обработка сигналов, таких как ctrl z, ctrl c...
     *
     * @param signalNames названия сигналов
     */
    private static void setSignalProcessing(String... signalNames) {
        for (String signalName : signalNames) {
            try {
                Signal.handle(new Signal(signalName), signal -> {
                });
            } catch (IllegalArgumentException ignored) {
                // Игнорируем исключение, если сигнал с таким названием уже существует или такого сигнала не существует
            }
        }
    }

}