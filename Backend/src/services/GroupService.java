package services;

import dto.Group;
import exceptions.GroupAlreadyExists;
import exceptions.InvalidUserInput;
import utils.FileHandler;

import java.util.List;
import java.util.Scanner;

public class GroupService {
    private static Scanner in = new Scanner(System.in);

    public static void addGroup() {
        System.out.print("Enter group name: ");
        String groupName = in.nextLine();
        validateGroupUserInput(groupName);

        List<Group> loadedGroups = FileHandler.loadAllGroups();
        validateFileGroupName(loadedGroups, groupName);

        Group newGroup = new Group(groupName);
        FileHandler.saveGroupToFile(newGroup, newGroup.getGroupName() + ".dat");
    }

    private static void validateGroupUserInput(String groupName) {
        if (groupName.isEmpty()) {
            throw new InvalidUserInput("It must contain at least a letter");
        }
    }

    private static void validateFileGroupName(List<Group> loadedGroups, String groupName) {
        if (doesGroupExist(loadedGroups, groupName)) {
            throw new GroupAlreadyExists("Group with this name already exists.");
        }
    }

    private static boolean doesGroupExist(List<Group> loadedGroups, String nameOfNewGroup) {
        for (Group loadedGroup : loadedGroups) {
            if (loadedGroup.getGroupName().equalsIgnoreCase(nameOfNewGroup)) {
                return true;
            }
        }
        return false;
    }
}
