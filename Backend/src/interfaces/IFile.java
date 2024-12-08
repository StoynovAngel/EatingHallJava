package interfaces;

import dto.Group;

import java.util.List;

public interface IFile {
    void saveGroupToFile(Group group);
    Group loadGroup(String fileName);
    List<Group> loadAllGroups();
}
