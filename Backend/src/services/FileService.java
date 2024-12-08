package services;

import dto.Group;
import interfaces.IFile;
import utils.FileHandler;

import java.util.List;

public class FileService implements IFile {
    private final FileHandler fileHandler;

    public FileService(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void saveGroupToFile(Group group) {
        fileHandler.saveGroupToFile(group);
    }

    @Override
    public Group loadGroup(String fileName) {
        return fileHandler.loadGroup(fileName);
    }

    @Override
    public List<Group> loadAllGroups() {
        return fileHandler.loadAllGroups();
    }
}
