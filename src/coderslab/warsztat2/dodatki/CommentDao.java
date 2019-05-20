//package coderslab.warsztat2.dodatki;
//
//import coderslab.warsztat2.zadanie1.ConnectionUtil;
//import coderslab.warsztat2.zadanie1.Solution;
//
//import java.sql.*;
//import java.util.Arrays;
//
//public class CommentDao {
//
//        private static final String CREATE_COMMENT_QUERY =
//                "INSERT INTO comment(solution_id, description, rating) VALUES (?, ?, ?, ?)";
//        private static final String READ_COMMENT_QUERY =
//                "SELECT * FROM comment where id = ?";
//        private static final String UPDATE_COMMENT_QUERY =
//                "UPDATE comment SET solution_id = ? rating = ?, description = ? where id = ?";
//        private static final String DELETE_COMMENT_QUERY =
//                "DELETE FROM solution WHERE id = ?";
//        private static final String FIND_ALL_COMMENTS_QUERY =
//                "SELECT * FROM comment";
//        private static final String FIND_ALL_BY_SOLUTION_ID =
//                "SELECT * FROM solution JOIN comment ON solution.id = comment.solution_id WHERE solution.id = ?";
//
//        public Comment create(Comment comment) {
//                try (Connection conn = ConnectionUtil.getConnection()) {
//
//                        PreparedStatement statement =
//                                conn.prepareStatement(CREATE_COMMENT_QUERY, Statement.RETURN_GENERATED_KEYS); // statement.return_generated_keys to jest id autoinkrementacji
//                        statement.setInt(1, comment.getSolutionId());
//                        statement.setString(2, comment.getComment());
//                        statement.setString(3, comment.getRating());
//                        statement.executeUpdate();
//                        ResultSet resultSet = statement.getGeneratedKeys();
//                        if (resultSet.next()) {
//                                comment.setId(resultSet.getInt(1));
//                        }
//                        return comment;
//                } catch (SQLException e) {
//                        e.printStackTrace();
//                        return null;
//                }
//        }
//
//        public Comment read(int commentId) {
//                try (Connection conn = ConnectionUtil.getConnection()) {
//                        PreparedStatement statement = conn.prepareStatement(READ_COMMENT_QUERY);
//                        statement.setInt(1, commentId);
//                        ResultSet resultSet = statement.executeQuery();
//                        if (resultSet.next()) {
//                                Comment comment = new Comment();
//                                comment.setId(resultSet.getInt("id"));
//                                comment.setSolutionId(resultSet.getInt("solution_id"));
//                                comment.setComment(resultSet.getString("description"));
//                                comment.setRating(resultSet.getString("rating"));
//                                return comment;
//                        }
//                } catch (SQLException e) {
//                        e.printStackTrace();
//                }
//                return null;
//        }
//
//        public void update(Comment comment) {
//                try (Connection conn = ConnectionUtil.getConnection()) {
//                        PreparedStatement statement = conn.prepareStatement(UPDATE_COMMENT_QUERY);
//                        statement.setInt(1, comment.getSolutionId());
//                        statement.setString(2, comment.getComment());
//                        statement.setString(3, comment.getRating());
//                        statement.executeUpdate();
//                } catch (SQLException e) {
//                        e.printStackTrace();
//                }
//        }
//
//        public void delete(int commentId) {
//                try (Connection conn = ConnectionUtil.getConnection()) {
//                        PreparedStatement statement = conn.prepareStatement(DELETE_COMMENT_QUERY);
//                        statement.setInt(1, commentId);
//                        statement.executeUpdate();
//                } catch (SQLException e) {
//                        e.printStackTrace();
//                }
//        }
//
//        public Comment[] findAll() {
//                try (Connection connection = ConnectionUtil.getConnection()) {
//                        Comment comment = new Comment();
//                        Comment[] comments = new Comment[0];
//                        PreparedStatement statement = connection.prepareStatement(FIND_ALL_COMMENTS_QUERY);
//                        ResultSet resultSet = statement.executeQuery();
//                        while (resultSet.next()) {
//                                comment.setId(resultSet.getInt("id"));
//                                comment.setSolutionId(resultSet.getInt("solution_id"));
//                                comment.setComment(resultSet.getString("description"));
//                                comment.setRating(resultSet.getString("rating"));
//                                comments = addToArray(comment, comments);
//                        }
//                        return comments;
//                } catch (SQLException e) {
//                        e.printStackTrace();
//                }
//                return null;
//        }
//
//    public Comment[] findAllBySolutionId(int solutionId) {
//
//        try (Connection conn = ConnectionUtil.getConnection()) {
//            Comment[] comments = new Comment[0];
//            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_SOLUTION_ID);
//            statement.setInt(1, solutionId);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Comment comment = new Comment();
//                comment.setId(resultSet.getInt("id"));
//                comment.setSolutionId(resultSet.getInt("solution_id"));
//                comment.setComment(resultSet.getString("description"));
//                comment.setRating(resultSet.getString("rating"));
//                comments = addToArray(comment, comments);
//            }
//            return comments;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//    public Comment[] addToArray(Comment comment, Comment[] comments) {
//        comments = Arrays.copyOf(comments, comments.length + 1);
//        comments[comments.length - 1] = comment;
//        return comments;
//    }
//}