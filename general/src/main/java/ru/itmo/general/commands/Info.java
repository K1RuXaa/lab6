package ru.itmo.general.commands;


import ru.itmo.general.commands.base.Command;
import ru.itmo.general.commands.base.CommandName;
import ru.itmo.general.managers.CollectionManager;
import ru.itmo.general.models.SpaceMarine;
import ru.itmo.general.network.Request;
import ru.itmo.general.network.Response;

import java.time.LocalDateTime;

/**
 * Команда 'info'. Выводит информацию о коллекции.
 *
 */
public class Info extends Command {
    private CollectionManager<SpaceMarine> spCollectionManager;

    public Info() {
        super(CommandName.INFO, "вывести информацию о коллекции");
    }

    /**
     * Конструктор для создания экземпляра команды Info.
     *
     * @param ticketCollectionManager менеджер коллекции билетов
     */
    public Info(CollectionManager<SpaceMarine> ticketCollectionManager) {
        this();
        this.spCollectionManager = ticketCollectionManager;
    }

    /**
     * Выполняет команду
     *
     * @param arguments аргументы команды
     * @return Успешность выполнения команды.
     */
    @Override
    public Response execute(Request arguments) {

        LocalDateTime spLastSaveTime = spCollectionManager.getLastSaveTime();
        String spLastSaveTimeString = (spLastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                spLastSaveTime.toLocalDate().toString() + " " + spLastSaveTime.toLocalTime().toString();

        String message;

        message = "Сведения о коллекции:" + '\n' +
                " Тип: " + spCollectionManager.collectionType() + '\n' +
                " Количество элементов SpaceMarine: " + spCollectionManager.collectionSize() + '\n' +
                " Дата последнего сохранения:" + spLastSaveTimeString;

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
        if (arguments.length > 1 && !arguments[1].isEmpty()) {
            return new Request(false, getName(), getUsingError());
        }

        return new Request(getName(), null);
    }
}