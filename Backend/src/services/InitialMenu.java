package services;

import interfaces.Menu;
import utils.consoleArt.ConsoleArt;

import java.util.Scanner;

public class InitialMenu implements Menu {
    @Override
    public void show() {
        System.out.println("1. Add a group");
        System.out.println("2. Display specific group.");
        System.out.println("3. Display a user from a group.");
        System.out.println("4. Display users from a group.");
        System.out.println("5. Get a specific grade");
        System.out.println("0. Exit...");
    }

    @Override
    public void handleUserChoice() {
        Scanner scanner = new Scanner(System.in);
        ConsoleArt.welcomeMessage();

        while(true) {
            show();
            System.out.print("Enter your choice (0-5): ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> GroupService.addGroup();
                case 2 -> GroupService.displaySpecificGroupFromFile();
                case 3 -> UserService.displayUserFromSpecificGroup();
                case 4 -> UserService.displayAllUsersFromSpecificGroup();
                case 5 -> UserService.displaySpecificUserGrades();
                case 0 -> {
                    System.out.println("Exiting the program...");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }

    }

}
