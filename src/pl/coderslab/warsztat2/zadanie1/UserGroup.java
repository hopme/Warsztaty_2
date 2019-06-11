package pl.coderslab.warsztat2.zadanie1;

public class UserGroup {
    private int id;
    private int userId;
    private int groupId;

    public UserGroup() {
    }

    public UserGroup(int id, int userId, int groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", userId=" + userId +
                ", groupId=" + groupId +
                '}';
    }
}