package pl.coderslab.warsztat2.zadanie1;

import java.sql.*;
import java.util.Arrays;

public class ExcerciseDao {
    private static final String CREATE_EXCERCISE_QUERY =
            "INSERT INTO excercise(title, description) VALUES (?, ?)";
    private static final String READ_EXCERCISE_QUERY =
            "SELECT * FROM excercise where id = ?";
    private static final String UPDATE_EXCERCISE_QUERY =
            "UPDATE excercise SET title = ?, description = ? where id = ?";
    private static final String DELETE_EXCERCISE_QUERY =
            "DELETE FROM excercise WHERE id = ?";
    private static final String FIND_ALL_EXCERCISE_QUERY =
            "SELECT * FROM excercise";
    private static final String FIND_ALL_NOT_ASSIGNED_TO_USER_ID =
            "SELECT * FROM excercise LEFT JOIN solution ON excercise.id = solution.excercise_id WHERE user_id != ? OR user_id is NULL";




    public Excercise create(Excercise excercise) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            PreparedStatement statement =
                    conn.prepareStatement(CREATE_EXCERCISE_QUERY, Statement.RETURN_GENERATED_KEYS); // statement.return_generated_keys to jest id autoinkrementacji
            statement.setString(1, excercise.getTitle());
            statement.setString(2, excercise.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                excercise.setId(resultSet.getInt(1));
            }
            return excercise;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Excercise read(int excerciseId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_EXCERCISE_QUERY);
            statement.setInt(1, excerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Excercise excercise = new Excercise();
                excercise.setId(resultSet.getInt("id"));
                excercise.setTitle(resultSet.getString("title"));
                excercise.setDescription(resultSet.getString("description"));
                return excercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Excercise excercise) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_EXCERCISE_QUERY);
            statement.setString(1, excercise.getTitle());
            statement.setString(2, excercise.getDescription());
            statement.setInt(3, excercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int excerciseId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_EXCERCISE_QUERY);
            statement.setInt(1, excerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Excercise[] findAll() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            Excercise[] excercises = new Excercise[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXCERCISE_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Excercise excercise = new Excercise();
                excercise.setId(resultSet.getInt("id"));
                excercise.setTitle(resultSet.getString("title"));
                excercise.setDescription(resultSet.getString("description"));
                excercises = addToArray(excercise, excercises);
            }
            return excercises;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Excercise[] findAllNotAssignedToUserId(int userId){
        try (Connection connection = ConnectionUtil.getConnection()){
            Excercise[] excercises = new Excercise[0];
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_NOT_ASSIGNED_TO_USER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Excercise excercise = new Excercise();
                excercise.setId(resultSet.getInt("id"));
                excercise.setTitle(resultSet.getString("title"));
                excercise.setDescription(resultSet.getString("description"));
                excercises = addToArray(excercise, excercises);
            }
            return excercises;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Excercise[] addToArray(Excercise excercise, Excercise[] excercises){
        excercises = Arrays.copyOf(excercises, excercises.length + 1);
        excercises[excercises.length-1] = excercise;
        return excercises;
    }


}

