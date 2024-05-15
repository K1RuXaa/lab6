package ru.itmo.general.managers;

import lombok.Getter;
import ru.itmo.general.commands.Add;
import ru.itmo.general.commands.Exit;
import ru.itmo.general.commands.Remove;
import ru.itmo.general.commands.Show;
import ru.itmo.general.commands.base.Command;
import ru.itmo.general.commands.base.CommandName;
import ru.itmo.general.models.SpaceMarine;
import ru.itmo.general.network.Request;
import ru.itmo.general.network.Response;
import ru.itmo.general.utils.console.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages commands.
 * Handles registration and execution of commands.
 *
 * @author zevtos
 */
public class CommandManager {
    /**
     * -- GETTER --
     * Retrieves the command history.
     */
    @Getter
    private static final List<String> commandHistory = new ArrayList<>();
    /**
     * -- GETTER --
     * Retrieves the command dictionary.
     */
    @Getter
    private static Map<String, Command> commands;

    /**
     * Registers a command.
     *
     * @param commandName The name of the command.
     * @param command     The command object.
     */
    public static void register(String commandName, Command command) {
        commands.put(commandName, command);
    }

    public static void init() {
        commands = new HashMap<>();
        register("exit", new Exit());
    }

    public static void initServerCommands(CollectionManager<SpaceMarine> spCollectionManager) {
        init();
        //  register("info", new Info(ticketCollectionManager));
        register("show", new Show(spCollectionManager));
        register("add", new Add(spCollectionManager));
        //  register("update", new Update(ticketCollectionManager, dao));
        register("remove_by_id", new Remove(spCollectionManager));
//        register("clear", new Clear(ticketCollectionManager));
//        register("remove_first", new RemoveFirst(ticketCollectionManager, dao));
//        register("remove_head", new RemoveHead(ticketCollectionManager, dao));
//        register("add_if_min", new AddIfMin(ticketCollectionManager));
//        register("sum_of_price", new SumOfPrice(ticketCollectionManager));
//        register("min_by_discount", new MinByDiscount(ticketCollectionManager));
//        register("max_by_name", new MaxByName(ticketCollectionManager));
//        register("register", new Register(userDao));
//        register("login", new Login(userDao));
    }

    public static void initClientCommands(Console console) {
        init();
//        register("help", new Help());
//        register("info", new Info());
        register("show", new Show());
        register("add", new Add(console));
        // register("update", new Update(console, ticketForm));
        register("remove_by_id", new Remove());
//        register("clear", new Clear());
        register("execute_script", new Command(CommandName.EXECUTE_SCRIPT, "Выполняет скрипт") {
        });
//        register("remove_first", new RemoveFirst());
//        register("remove_head", new RemoveHead());
//        register("add_if_min", new AddIfMin(console, ticketForm));
//        register("sum_of_price", new SumOfPrice());
//        register("min_by_discount", new MinByDiscount());
//        register("max_by_name", new MaxByName());
//        register("history", new History());
//        register("register", new Register(console));
//        register("login", new Login(console));
    }


    /**
     * Processes the command received from the client.
     * Executes the command if it exists in the command dictionary.
     * If the command does not exist, returns a Response indicating that the command was not found.
     *
     * @param request The request containing the command to be processed.
     * @return The Response generated after processing the command.
     */
    public static Response handle(Request request) {
        var command = getCommands().get(request.getCommand());
        if (command == null) return new Response(false, request.getCommand(), "Command not found!");
        if (!"exit".equals(request.getCommand()) && !"save".equals(request.getCommand())) {
            return command.execute(request);
        }
        return new Response(false, "Unknown command");
    }

    /**
     * Processes the command received from the server console.
     * Executes the command if it exists in the command dictionary.
     * If the command does not exist, returns without performing any action.
     * If the command is "exit" or "save", executes the command.
     *
     * @param request The request containing the command to be processed.
     */
    public static void handleServer(Request request) {
        var command = getCommands().get(request.getCommand());
        if (command == null) return;
        if ("exit".equals(request.getCommand())) command.execute(request);
    }


    /**
     * Adds a command to the history.
     *
     * @param command The command.
     */
    public static void addToHistory(String command) {
        commandHistory.add(command);
    }
}