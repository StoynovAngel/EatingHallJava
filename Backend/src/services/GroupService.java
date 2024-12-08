package services;

import dto.Group;
import exceptions.GroupAlreadyExists;
import exceptions.InvalidGroup;
import exceptions.InvalidUserInput;
import interfaces.IGroup;
import utils.FileHandler;
import java.util.List;
import java.util.Scanner;

public class GroupService implements IGroup {
    private final Scanner in = new Scanner(System.in);
    private final FileService fileService;

    public GroupService(FileService fileService) {
        this.fileService = fileService;
    }

    public void addGroup() {
        System.out.print("Enter group name: ");
        String groupName = in.nextLine();
        validateGroupUserInput(groupName);

        List<Group> loadedGroups = fileService.loadAllGroups();
        validateFindFileGroupName(loadedGroups, groupName);

        Group newGroup = new Group(groupName);
        fileService.saveGroupToFile(newGroup);
    }

    private void validateGroupUserInput(String groupName) {
        if (groupName.isEmpty()) {
            throw new InvalidUserInput("It must contain at least a letter");
        }
    }

    private void validateFindFileGroupName(List<Group> loadedGroups, String groupName) {
        if (doesGroupExist(loadedGroups, groupName)) {
            throw new GroupAlreadyExists("Group with this name already exists.");
        }
    }

    private boolean doesGroupExist(List<Group> loadedGroups, String nameOfNewGroup) {
        for (Group loadedGroup : loadedGroups) {
            if (loadedGroup.getGroupName().equalsIgnoreCase(nameOfNewGroup)) {
                return true;
            }
        }
        return false;
    }

    public void displaySpecificGroupFromFile() {
        System.out.print("Enter a group name: ");
        String searchedGroupName = in.nextLine();
        validateGroupUserInput(searchedGroupName);
        Group loadedGroup = getSpecificLoadedGroupByName(searchedGroupName);
        System.out.println(loadedGroup);
    }

    private Group getSpecificLoadedGroupByName(String searchedGroupName) {
        Group loadedGroup = fileService.loadGroup(searchedGroupName);
        if (loadedGroup == null) {
            throw new InvalidGroup("Group with this name could not be find.");
        }
        return loadedGroup;
    }
}
