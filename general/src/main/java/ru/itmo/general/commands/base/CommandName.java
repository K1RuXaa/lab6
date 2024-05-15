package ru.itmo.general.commands.base;

/**
 * Enum representing different command names.
 */
public enum CommandName {
    HELP,                // Command to display help information
    INFO,                // Command to display information about the collection
    SHOW,                // Command to display all elements of the collection
    ADD,                 // Command to add an element to the collection
    UPDATE,              // Command to update an element in the collection
    REMOVE_BY_ID,        // Command to remove an element from the collection by its ID
    CLEAR,               // Command to clear the collection
    EXIT,                // Command to exit the program
    HEAD,        // Command to get the first element from the collection
    MAX_BY_LOYAL,         // Command to remove the head element from the collection
    PRINT_DESCENDING,          // Command to add an element to the collection if it is the minimum element
    PRINT_UNIQUE_WEAPON_TYPE,        // Command to calculate the sum of prices of all elements in the collection
    HISTORY,             // Command to display command history
    EXECUTE_SCRIPT,      // Command to execute commands from a script file
    EXIT_SERVER,         // Command to exit the server

}
