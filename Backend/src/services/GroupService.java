package services;

import dto.Group;
import exceptions.GroupAlreadyExists;
import exceptions.InvalidGroup;
import exceptions.InvalidUserInput;
import utils.FileHandler;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class GroupService {
    private static Scanner in = new Scanner(System.in);

    public static void addGroup() {
        System.out.print("Enter group name: ");
        String groupName = in.nextLine();
        validateGroupUserInput(groupName);

        List<Group> loadedGroups = FileHandler.loadAllGroups();
        validateFindFileGroupName(loadedGroups, groupName);

        Group newGroup = new Group(groupName);
        FileHandler.saveGroupToFile(newGroup, newGroup.getGroupName());
    }

    private static void validateGroupUserInput(String groupName) {
        if (groupName.isEmpty()) {
            throw new InvalidUserInput("It must contain at least a letter");
        }
    }

    private static void validateFindFileGroupName(List<Group> loadedGroups, String groupName) {
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

    public static void displaySpecificGroupFromFile() {
        System.out.print("Enter a group name: ");
        String searchedGroupName = in.nextLine();
        validateGroupUserInput(searchedGroupName);
        Group loadedGroup = getSpecificLoadedGroupByName(searchedGroupName);
        System.out.println(loadedGroup);
    }

    private static Group getSpecificLoadedGroupByName(String searchedGroupName) {
        Group loadedGroup = FileHandler.loadGroup(searchedGroupName);
        if (loadedGroup == null) {
            throw new InvalidGroup("Group with this name could not be find.");
        }
        return loadedGroup;
    }
}
