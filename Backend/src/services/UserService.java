package services;

import dto.Grade;
import dto.Group;
import dto.User;
import exceptions.GroupNotFoundException;
import exceptions.InvalidUserInput;
import exceptions.UserNotFoundException;
import utils.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private static final Scanner in = new Scanner(System.in);

    public static void displaySpecificUserGrades() {
        Group loadedGroup = getSpecificGroupFromFile();
        User user = getSpecificUser(loadedGroup);
        System.out.println(user.getGrades());
    }

    public static void displayUserFromSpecificGroup() {
        Group loadedGroup = getSpecificGroupFromFile();
        User user = getSpecificUser(loadedGroup);
        System.out.println(user);
    }

    public static void displayAllUsersFromSpecificGroup() {
        Group loadedGroup = getSpecificGroupFromFile();
        List<User> members = loadedGroup.getGroupMembers();

        if (members.isEmpty()) {
            System.out.println("No users found in this group.");
        } else {
            members.forEach(System.out::println);
        }
    }

    private static Group getSpecificGroupFromFile() {
        System.out.print("Enter group name: ");
        String searchedGroupName = in.nextLine();
        Group group = FileHandler.loadGroup(searchedGroupName);

        if (group == null || !group.getGroupName().equals(searchedGroupName)) {
            throw new GroupNotFoundException("Could not find group with the name: " + searchedGroupName);
        }

        return group;
    }

    private static User getSpecificUser(Group loadedGroup) {
        List<User> usersFromGroup = loadedGroup.getGroupMembers();
        System.out.print("Enter username: ");
        String searchedUsername = in.nextLine();
        validateUserInput(searchedUsername);

        return usersFromGroup.stream()
                .filter(user -> user.getUsername().equals(searchedUsername))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with username '" + searchedUsername + "' not found."));
    }

    private static void validateUserInput(String username) {
        if (!username.matches("^[a-zA-Z]{4,}$")) {
            System.out.println("Invalid input. Username must contain only alphabetic characters and be at least 4 letters.");
            throw new InvalidUserInput("Invalid username: " + username);
        }
    }

    public static void addNewUserToGroup() {
        Group group = getSpecificGroupFromFile();
        List<User> users = group.getGroupMembers();
        User newUser = createUser();
        if (!users.contains(newUser)) {
            users.add(newUser);
        }else {
            System.out.println("This users already exists...");
        }
        FileHandler.saveGroupToFile(group);
    }

    private static User createUser() {
        System.out.print("Username: ");
        String username = in.nextLine();
        User user = new User(username);
        while (true) {
            Grade newGrade = addGradeToUser();
            user.getGrades().add(newGrade);

            System.out.println("Add another grade? (y/n): ");
            String choice = in.nextLine().toLowerCase().trim();
            if (!choice.equals("y")) {
                System.out.println("Finished adding grades.");
                break;
            }
        }
        return user;
    }

    private static Grade addGradeToUser() {
        System.out.print("Subject: ");
        String subject = in.nextLine();
        System.out.print("Mark: ");
        double mark = in.nextDouble();
        in.nextLine();
        return new Grade(subject, mark);
    }
}
