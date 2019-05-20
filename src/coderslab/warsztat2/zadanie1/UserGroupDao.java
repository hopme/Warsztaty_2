package coderslab.warsztat2.zadanie1;

import java.sql.*;
import java.util.Arrays;

public class UserGroupDao {
    private static final String CREATE_USER_GROUP_QUERY =
            "INSERT INTO user_group(user_id, group_id) VALUES (?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM user_group where id = ?";
    private static final String UPDATE_USER_GROUP_QUERY =
            "UPDATE user_group SET user_id = ?, group_id = ? where id = ?";
    private static final String DELETE_USER_GROUP_QUERY =
            "DELETE FROM user_group WHERE id = ?";

    public UserGroup create(UserGroup userGroup) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS); // statement.return_generated_keys to jest id autoinkrementacji
            statement.setInt(1, userGroup.getUserId());
            statement.setInt(2, userGroup.getGroupId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userGroup.setId(resultSet.getInt(1));
            }
            return userGroup;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserGroup read(int userGroupId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userGroupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getInt("id"));
                userGroup.setUserId(resultSet.getInt("user_id"));
                userGroup.setGroupId(resultSet.getInt("group_id"));
                return userGroup;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(UserGroup userGroup) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_GROUP_QUERY);
            statement.setInt(1, userGroup.getUserId());
            statement.setInt(2, userGroup.getGroupId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userGroupId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_GROUP_QUERY);
            statement.setInt(1, userGroupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   private UserGroup[] addToArray(UserGroup userGroup, UserGroup[] userGroups) {
        userGroups = Arrays.copyOf(userGroups, userGroups.length + 1);
        userGroups[userGroups.length - 1] = userGroup;
        return userGroups;

    }
}
