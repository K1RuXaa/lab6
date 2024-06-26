package ru.itmo.general.commands;

import ru.itmo.general.commands.base.Command;
import ru.itmo.general.commands.base.CommandName;
import ru.itmo.general.managers.CollectionManager;
import ru.itmo.general.models.SpaceMarine;
import ru.itmo.general.network.Request;
import ru.itmo.general.network.Response;

/**
 * Команда 'show'. Выводит все элементы коллекции.
 *
 * @author zevtos
 */
public class Show extends Command {
    private CollectionManager<SpaceMarine> ticketCollectionManager;

    public Show() {
        super(CommandName.SHOW, "вывести все элементы коллекции Ticket");
    }

    /**
     * Конструктор для создания экземпляра команды Show.
     *
     * @param ticketCollectionManager менеджер коллекции
     */
    public Show(CollectionManager<SpaceMarine> ticketCollectionManager) {
        this();
        this.ticketCollectionManager = ticketCollectionManager;
    }

    /**
     * Выполняет команду
     *
     * @param arguments аргументы команды
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request arguments) {

        String message = ticketCollectionManager.toString();
        return new Response(true, null, message);
    }

    /**
     * Выполняет команду
     *
     * @param arguments аргументы команды
     * @return Успешность выполнения команды.
     */
    @Override
    public Request execute(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            return new Request(false, getName(), getUsingError());
        }
        return new Request(getName(), null);
    }
}