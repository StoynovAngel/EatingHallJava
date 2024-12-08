package utils;

import dto.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private final String DIRECTORY = "files/";
    private final String FILE_EXTENSION = ".dat";
    {
        createDirectoryIfNotExists();
    }
    public void saveGroupToFile(Group group) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream((DIRECTORY + group.getGroupName() + FILE_EXTENSION)))) {
            oos.writeObject(group);
            System.out.println("Group saved to " + (DIRECTORY + group.getGroupName()));
        } catch (IOException e) {
            throw new RuntimeException("Error saving group to file", e);
        }
    }
    public Group loadGroup(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream((DIRECTORY + fileName + FILE_EXTENSION)))) {
            return (Group) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading groups from file", e);
        }
    }
    public List<Group> loadAllGroups() {
        List<Group> groups = new ArrayList<>();
        File directory = new File(DIRECTORY);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(FILE_EXTENSION));
        if (files == null || files.length == 0) {
            System.out.println("No group files found in directory: " + DIRECTORY);
            return groups;
        }
        for (File file: files) {
            try {
                Group group = loadGroup(file.getName());
                groups.add(group);
            } catch (RuntimeException e) {
                System.err.println("Failed to load group from file: " + file.getName() + ". Error: " + e.getMessage());
            }
        }
        return groups;
    }
    private void createDirectoryIfNotExists() {
        File directory = new File(DIRECTORY);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + DIRECTORY);
            } else {
                System.err.println("Failed to create directory: " + DIRECTORY);
            }
        }
    }
}
