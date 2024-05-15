package ru.itmo.general.commands;


import ru.itmo.general.commands.base.Command;
import ru.itmo.general.commands.base.CommandName;
import ru.itmo.general.exceptions.EmptyValueException;
import ru.itmo.general.exceptions.InvalidNumberOfElementsException;
import ru.itmo.general.exceptions.NotFoundException;
import ru.itmo.general.managers.CollectionManager;
import ru.itmo.general.models.SpaceMarine;
import ru.itmo.general.network.Request;
import ru.itmo.general.network.Response;
import ru.itmo.general.utils.console.Console;


/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции по ID.
 *
 * @author zevtos
 */
public class Remove extends Command {
    private Console console;
    private CollectionManager<SpaceMarine> spaceMarineCollectionManager;

    public Remove() {
        super(CommandName.REMOVE_BY_ID, "<ID> удалить spaceMarine из коллекции по ID");
    }

    /**
     * Конструктор для создания экземпляра команды Remove.
     */
    public Remove(CollectionManager<SpaceMarine> spCollectionManager) {
        this();
        this.spaceMarineCollectionManager = spCollectionManager;
    }

    /**
     * Выполняет команду
     *
     * @param request аргументы команды
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request request) {
        try {

            if (spaceMarineCollectionManager.collectionSize() == 0) throw new EmptyValueException();

            var id = ((long) request.getData());

            if (!spaceMarineCollectionManager.remove(id)) throw new NotFoundException();

            return new Response(true, "Билет успешно удален.");

        } catch (EmptyValueException exception) {
            return new Response(false, "Коллекция пуста!");
        } catch (NotFoundException exception) {
            return new Response(false, "Билета с таким ID в коллекции не существует!");
        }
    }

    /**
     * Выполняет команду
     *
     * @param arguments аргументы команды
     * @return Успешность выполнения команды.
     */
    @Override
    public Request execute(String[] arguments) {
        try {
            if (arguments.length < 2 || arguments[1].isEmpty()) throw new InvalidNumberOfElementsException();

            int id = Integer.parseInt(arguments[1]);
            return new Request(getName(), id);
        } catch (InvalidNumberOfElementsException exception) {
            return new Request(false, getName(), getUsingError());
        } catch (NumberFormatException exception) {
            return new Request(false, getName(), "ID должен быть представлен числом!");
        }
    }
}