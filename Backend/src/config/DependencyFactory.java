package config;

import services.*;
import utils.FileHandler;

public class DependencyFactory {
    public FileHandler createFileHandler() {
        return new FileHandler();
    }

    public FileService createFileService() {
        return new FileService(createFileHandler());
    }

    public UserService createUserService() {
        return new UserService(createFileService());
    }

    public GroupService createGroupService() {
        return new GroupService(createFileService());
    }

    public InitialMenu createInitialMenu() {
        return new InitialMenu(createUserService(), createGroupService());
    }
}
