package pl.coderslab.warsztat2.zadanie1;

import java.sql.*;
import java.util.Arrays;

public class SolutionDao {

        private static final String CREATE_SOLUTION_QUERY =
                "INSERT INTO solution(created, updated, description, excercise_id, user_id) VALUES (?, ?, ?, ?, ?)";
        private static final String READ_SOLUTION_QUERY =
                "SELECT * FROM solution where id = ?";
        private static final String UPDATE_SOLUTION_QUERY =
                "UPDATE solution SET created = ?, updated = ?, description = ? where id = ?";
        private static final String DELETE_SOLUTION_QUERY =
                "DELETE FROM solution WHERE id = ?";
        private static final String FIND_ALL_SOLUTION_QUERY =
                "SELECT * FROM solution";
        private static final String FIND_ALL_BY_USER_ID =
                "SELECT * FROM users JOIN solution ON users.id = solution.user_id WHERE users.id = ?";
        private static final String FIND_ALL_BY_EXCERCISE_ID =
                "SELECT * FROM excercise JOIN solution ON excercise.id = solution.excercise_id WHERE excercise.id = ? ORDER BY created ASC";

        public Solution create(Solution solution) {
                try (Connection conn = ConnectionUtil.getConnection()) {

                        PreparedStatement statement =
                                conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS); // statement.return_generated_keys to jest id autoinkrementacji
                        statement.setString(1, solution.getCreated());
                        statement.setString(2, solution.getUpdated());
                        statement.setString(3, solution.getDescription());
                        statement.setInt(4, solution.getExcerciseId());
                        statement.setInt(5, solution.getUserId());
                        statement.executeUpdate();
                        ResultSet resultSet = statement.getGeneratedKeys();
                        if (resultSet.next()) {
                                solution.setId(resultSet.getInt(1));
                        }
                        return solution;
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        public Solution read(int solutionId) {
                try (Connection conn = ConnectionUtil.getConnection()) {
                        PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
                        statement.setInt(1, solutionId);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                                Solution solution = new Solution();
                                solution.setId(resultSet.getInt("id"));
                                solution.setCreated(resultSet.getString("created"));
                                solution.setUpdated(resultSet.getString("updated"));
                                solution.setDescription(resultSet.getString("description"));
                                return solution;
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return null;
        }

        public void update(Solution solution) {
                try (Connection conn = ConnectionUtil.getConnection()) {
                        PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
                        statement.setString(1, solution.getCreated());
                        statement.setString(2, solution.getUpdated());
                        statement.setString(3, solution.getDescription());
                        statement.setInt(4, solution.getId());
                        statement.executeUpdate();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public void delete(int solutionId) {
                try (Connection conn = ConnectionUtil.getConnection()) {
                        PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
                        statement.setInt(1, solutionId);
                        statement.executeUpdate();
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

        public Solution[] findAll() {
                try (Connection connection = ConnectionUtil.getConnection()) {
                        Solution solution = new Solution();
                        Solution[] solutions = new Solution[0];
                        PreparedStatement statement = connection.prepareStatement(FIND_ALL_SOLUTION_QUERY);
                        ResultSet resultSet = statement.executeQuery();
                        while (resultSet.next()) {
                                solution.setId(resultSet.getInt("id"));
                                solution.setCreated(resultSet.getString("created"));
                                solution.setUpdated(resultSet.getString("updated"));
                                solution.setDescription(resultSet.getString("description"));
                                solutions = addToArray(solution, solutions);
                        }
                        return solutions;
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return null;
        }

    public Solution[] findAllByExcerciseId(int excerciseId) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_EXCERCISE_ID);
            statement.setInt(1, excerciseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("solution.id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Solution[] findAllByUserId(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("solution.id"));
                solution.setCreated(resultSet.getString("created"));
                solution.setUpdated(resultSet.getString("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExcerciseId(resultSet.getInt("excercise_id"));
                solution.setUserId(resultSet.getInt("user_Id"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Solution[] addToArray(Solution solution, Solution[] solutions) {
        solutions = Arrays.copyOf(solutions, solutions.length + 1);
        solutions[solutions.length - 1] = solution;
        return solutions;
    }
}