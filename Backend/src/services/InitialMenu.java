package services;

import interfaces.IGroup;
import interfaces.IUser;
import interfaces.Menu;
import utils.consoleArt.ConsoleArt;

import java.util.Scanner;

public class InitialMenu implements Menu {
    private final IUser userService;
    private final IGroup groupService;

    public InitialMenu(IUser userService, IGroup groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }
    @Override
    public void show() {
        System.out.println("1. Add a group");
        System.out.println("2. Display specific group.");
        System.out.println("3. Display a user from a group.");
        System.out.println("4. Display users from a group.");
        System.out.println("5. Get a specific grade");
        System.out.println("6. Add user to a group.");
        System.out.println("7. Update user's grade.");
        System.out.println("8. Delete user's grade.");
        System.out.println("9. Add new grade");
        System.out.println("0. Exit...");
    }

    @Override
    public void handleUserChoice() {
        Scanner scanner = new Scanner(System.in);
        ConsoleArt.welcomeMessage();

        while(true) {
            show();
            System.out.print("Enter your choice (0-6): ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> groupService.addGroup();
                case 2 -> groupService.displaySpecificGroupFromFile();
                case 3 -> userService.displayUserFromSpecificGroup();
                case 4 -> userService.displayAllUsersFromSpecificGroup();
                case 5 -> userService.displaySpecificUserGrades();
                case 6 -> userService.addNewUserToGroup();
                case 7 -> userService.updateUserGrade();
                case 8 -> userService.deleteUserGrade();
                case 9 -> userService.addNewGradeToUser();
                case 0 -> {
                    System.out.println("Exiting the program...");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }

    }

}
