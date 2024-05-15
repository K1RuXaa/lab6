package ru.itmo.general.utils.console;

import java.util.Scanner;

public class InputParser {
    private Scanner scanner;

    public InputParser() {
        this.scanner = Interrogator.getUserScanner();
    }

    public int requestInt(String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
        return value;
    }


    public double requestDouble(String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a double.");
            }
        }
        return value;
    }

    public String requestString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public boolean requestBoolean(String prompt) {
        boolean value;
        while (true) {
            System.out.print(prompt + " (true/false): ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                value = Boolean.parseBoolean(input);
                break;
            } else {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }
        return value;
    }

    public long requestLong(String prompt) {
        long value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Long.parseLong(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
        return value;
    }

    // Other methods for different data types can be added here.
}
