package services;

import interfaces.Menu;

import java.util.Scanner;

public class InitialMenu implements Menu {
    @Override
    public void show() {
        System.out.println("""
                
                 __    __     _                         \s
                / / /\\ \\ \\___| | ___ ___  _ __ ___   ___\s
                \\ \\/  \\/ / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\
                 \\  /\\  /  __/ | (_| (_) | | | | | |  __/
                  \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|
                                                        \s
                """);
        System.out.println("1. Add a group");
        System.out.println("2. Display specific group.");
        System.out.println("2. Get a user from a group.");
        System.out.println("3. Get a specific grade");
        System.out.println("0. Exit...");
    }

    @Override
    public void handleUserChoice() {
        Scanner scanner = new Scanner(System.in);
        show();
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();

        while(true) {
            switch (choice) {
                case 1 -> {
                    GroupService.addGroup();
                    return;
                }
                case 2 -> {
                    GroupService.displaySpecificGroupFromFile();
                    return;
                }
                case 0 -> {
                    System.out.println("Exiting the program...");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }

    }

}
