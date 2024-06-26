package ru.itmo.general.commands;

import ru.itmo.general.commands.base.Command;
import ru.itmo.general.commands.base.CommandName;
import ru.itmo.general.network.Request;


/**
 * Команда 'help'. Выводит справку по доступным командам.
 *
 * @author zevtos
 */
public class Help extends Command {

    /**
     * Конструктор для создания экземпляра команды Help.
     */
    public Help() {
        super(CommandName.HELP, "вывести справку по доступным командам");
    }

    /**
     * Выполняет команду.
     *
     * @param arguments аргументы команды (в данном случае ожидается отсутствие аргументов)
     * @return Успешность выполнения команды
     */
    @Override
    public Request execute(String[] arguments) {
        if (arguments.length > 1 && !arguments[1].isEmpty()) {
            return new Request(false, getName(), getUsingError());
        }

        return new Request(getName(), null);
    }
}