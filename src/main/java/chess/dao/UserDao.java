package chess.dao;

import java.sql.SQLException;

public class UserDao {
    private UserDao() {
    }

    public static boolean makeUserOf(String userName, String userId, String userPassword) {
        if (isDuplicationIdOrName(userName, userId)) {
            return false;
        }
        final var query = "INSERT INTO user VALUES(?, ?, ?)";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPassword);
            preparedStatement.setString(3, userName);
            var resultSet = preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isDuplicationIdOrName(String userName, String userId) {
        final var query = "SELECT * FROM user WHERE user_id = ? or user_name = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userName);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserNameOf(String userId, String userPassword) {
        final var query = "SELECT user_name FROM user WHERE user_id = ? or user_password = ?";
        try (var connection = ConnectionHandler.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPassword);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("user_name");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
