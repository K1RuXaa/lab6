package ru.itmo.general.commands;


import ru.itmo.general.commands.base.Command;
import ru.itmo.general.commands.base.CommandName;
import ru.itmo.general.exceptions.InvalidFormException;
import ru.itmo.general.exceptions.InvalidNumberOfElementsException;
import ru.itmo.general.managers.CollectionManager;
import ru.itmo.general.models.SpaceMarine;
import ru.itmo.general.models.SpaceMarineReader;
import ru.itmo.general.network.Request;
import ru.itmo.general.network.Response;
import ru.itmo.general.utils.console.Console;


/**
 * add a new element to the collection
 */
public class Add extends Command {
    private Console console;
    private CollectionManager<SpaceMarine> spaceMarineCollectionManager;

    //  private CollectionManager<Ticket> ticketCollectionManager;
    public Add() {
        super(CommandName.ADD, "{element} добавить новый объект Ticket в коллекцию");
    }

    public Add(CollectionManager<SpaceMarine> spCollectionManager) {
        this();
        this.spaceMarineCollectionManager = spCollectionManager;
    }

    public Add(Console console) {
        this();
        this.console = console;
    }

    @Override
    public Request execute(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new InvalidNumberOfElementsException();
            console.println("* Создание нового продукта:");

            var newSpaceMarine = new SpaceMarineReader()
                    .setChapter()
                    .setCoordinates()
                    .setHealth()
                    .setName()
                    .setLoyal()
                    .setMeleeWeapon()
                    .setWeaponType()
                    .build();

            return new Request(getName(), newSpaceMarine);

        } catch (InvalidNumberOfElementsException exception) {
            return new Request(false, getName(), getUsingError());
        } catch (InvalidFormException exception) {
            return new Request(false, getName(), "Поля десантника не валидны! SpaceMarine не создан!" + '\n' + exception.getMessage());
        }
    }

    /**
     * Выполняет команду.
     *
     * @param request запрос на добавление билета
     * @return Успешность выполнения команды
     */
    @Override
    public Response execute(Request request) {
        try {
            var marine = ((SpaceMarine) request.getData());
            // if (!marine.validate()) {
            //     return new Response(false, "Билет не добавлен, поля билета не валидны!"); //todo доделать валидность
            // }

            if (!spaceMarineCollectionManager.add(marine))
                return new Response(false, "Билет уже существует", -1);
            return new Response(true, "Билет успешно добавлен", null);
        } catch (Exception e) {
            return new Response(false, e.toString(), -1);
        }
    }
}
