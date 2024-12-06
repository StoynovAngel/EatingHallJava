package services;

import dto.Group;
import dto.User;
import exceptions.InvalidGroup;
import exceptions.InvalidUserInput;
import utils.FileHandler;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private static final Scanner in = new Scanner(System.in);

    public static void displayUserFromSpecificGroup() {
        System.out.print("Enter group name: ");
        String searchedGroupName = in.nextLine();

        Group loadedGroup = FileHandler.loadGroup(searchedGroupName);
        if (checkExistingGroup(loadedGroup, searchedGroupName)) {
            getSpecificUser(loadedGroup);
        }
    }

    public static void displayUsersFromSpecificGroup() {
        System.out.print("Enter group name: ");
        String searchedGroupName = in.nextLine();

        Group loadedGroup = FileHandler.loadGroup(searchedGroupName);
        if (checkExistingGroup(loadedGroup, searchedGroupName)) {
            System.out.println(loadedGroup.getGroupMembers());
        }
    }

    private static boolean checkExistingGroup(Group loadedGroup, String searchedGroupName) {
        if (!loadedGroup.getGroupName().equals(searchedGroupName)) {
            throw new InvalidGroup("Group with this name does not exit");
        }
        return true;
    }

    private static void getSpecificUser(Group loadedGroup) {
        List<User> usersFromGroup = loadedGroup.getGroupMembers();
        System.out.print("Enter username: ");
        String searchedUsername = in.nextLine();
        validateUserInput(searchedUsername);
        usersFromGroup.stream().filter(user -> user.getUsername().equals(searchedUsername)).forEach(System.out::println);
    }

    private static void validateUserInput(String username) {
        if(!username.matches("^[a-zA-Z]{4,}$")) {
            throw new InvalidUserInput("Username must contain only alphabetic characters and be at least 4 letters.");
        }
    }
}
