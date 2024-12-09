package services;

import dto.Grade;
import dto.Group;
import dto.User;
import exceptions.GradeNotFound;
import exceptions.GroupNotFoundException;
import exceptions.InvalidUserInput;
import exceptions.UserNotFoundException;
import interfaces.IUser;
import java.util.List;
import java.util.Scanner;

public class UserService implements IUser {
    private final Scanner in = new Scanner(System.in);
    private final FileService fileService;
    private final GradeService gradeService = new GradeService();

    public UserService(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void displaySpecificUserGrades() {
        Group loadedGroup = getSpecificGroupFromFile();
        User user = getSpecificUser(loadedGroup);
        System.out.println(user.getGrades());
    }

    @Override
    public void displayUserFromSpecificGroup() {
        Group loadedGroup = getSpecificGroupFromFile();
        User user = getSpecificUser(loadedGroup);
        System.out.println(user);
    }

    @Override
    public void displayAllUsersFromSpecificGroup() {
        Group loadedGroup = getSpecificGroupFromFile();
        List<User> members = loadedGroup.getGroupMembers();

        if (members.isEmpty()) {
            System.out.println("No users found in this group.");
        } else {
            members.forEach(System.out::println);
        }
    }

    private Group getSpecificGroupFromFile() {
        System.out.print("Enter group name: ");
        String searchedGroupName = in.nextLine();
        Group group = fileService.loadGroup(searchedGroupName);

        if (group == null || !group.getGroupName().equals(searchedGroupName)) {
            throw new GroupNotFoundException("Could not find group with the name: " + searchedGroupName);
        }

        return group;
    }

    @Override
    public void updateUserGrade() {
        Group specificGroup = getSpecificGroupFromFile();
        User user = getSpecificUser(specificGroup);
        List<Grade> grades = user.getGrades();

        System.out.println("What grade do you want to change? Write the subject's name: ");
        String inputSubject = in.nextLine();

        Grade filteredGrade = grades.stream()
                .filter(grade -> grade.getSubject().equals(inputSubject))
                .findFirst()
                .orElseThrow(() -> new GradeNotFound("No such subject found - " + inputSubject));

        gradeService.updateGrade(filteredGrade);
        fileService.saveGroupToFile(specificGroup);
    }

    @Override
    public void deleteUserGrade() {
        Group specificGroup = getSpecificGroupFromFile();
        User user = getSpecificUser(specificGroup);
        List<Grade> grades = user.getGrades();

        System.out.println("What grade do you want to change? Write the subject's name: ");
        String inputSubject = in.nextLine();

        for (Grade grade: grades) {
            if(grade.getSubject().equals(inputSubject)) {
                grades.remove(grade);
                fileService.saveGroupToFile(specificGroup);
                return;
            }
        }
        throw new GradeNotFound("No such grade found");
    }

    private List<Grade> getSpecificUserGrades() {
        Group specificGroup = getSpecificGroupFromFile();
        User user = getSpecificUser(specificGroup);
        fileService.saveGroupToFile(specificGroup);
        return user.getGrades();
    }

    private User getSpecificUser(Group loadedGroup) {
        List<User> usersFromGroup = loadedGroup.getGroupMembers();
        System.out.print("Enter username: ");
        String searchedUsername = in.nextLine();
        validateUserInput(searchedUsername);

        return usersFromGroup.stream()
                .filter(user -> user.getUsername().equals(searchedUsername))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with username '" + searchedUsername + "' not found."));
    }

    private void validateUserInput(String username) {
        if (!username.matches("^[a-zA-Z]{4,}$")) {
            System.out.println("Invalid input. Username must contain only alphabetic characters and be at least 4 letters.");
            throw new InvalidUserInput("Invalid username: " + username);
        }
    }

    @Override
    public void addNewUserToGroup() {
        Group group = getSpecificGroupFromFile();
        List<User> users = group.getGroupMembers();
        User newUser = createUser();
        if (!users.contains(newUser)) {
            users.add(newUser);
        }else {
            System.out.println("This users already exists...");
        }
        fileService.saveGroupToFile(group);
    }

    @Override
    public void addNewGradeToUser() {
        Group specificGroup = getSpecificGroupFromFile();
        User user = getSpecificUser(specificGroup);
        List<Grade> grades = user.getGrades();
        gradeService.addGrade(grades);
        fileService.saveGroupToFile(specificGroup);
    }

    private User createUser() {
        System.out.print("Username: ");
        String username = in.nextLine();
        User user = new User(username);
        while (true) {
            Grade newGrade = gradeService.addGradeToUser();
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

}
