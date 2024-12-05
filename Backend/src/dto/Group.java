package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    private String groupName;
    private List<User> groupMembers;

    public Group(String groupName, List<User> groupMembers) {
        this.groupName = groupName;
        this.groupMembers = groupMembers;
    }

    public Group(String groupName) {
        this.groupName = groupName;
        this.groupMembers = new ArrayList<>();
    }

    public String getGroupName() {
        return groupName;
    }

    public List<User> getGroupMembers() {
        return groupMembers;
    }

    @Override
    public String toString() {
        StringBuilder members = new StringBuilder();
        for (User user : groupMembers) {
            members.append("\t").append(user.toString()).append("\n");
        }

        return "Group {\n" +
                "\tgroupName: '" + groupName + "',\n" +
                "\tgroupMembers: [\n" + members + "\t]\n" +
                "}";
    }
}
