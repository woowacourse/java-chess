package chess.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.game.User;

public class LoginDao {

    private static final String SERVER = "localhost:13306";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    private final Database database;

    public LoginDao(final DatabaseName databaseName) {
        database = new Database(databaseName);
    }

    public User getUserById(String id) {
        final String query = "SELECT * FROM User WHERE user_id = ?";
        try (
                Connection connection = database.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            String userId = resultSet.getString("user_id");
            String nickname = resultSet.getString("nickname");
            return new User(userId, nickname);
        } catch (SQLException e) {
            return null;
        }
    }

    public void addUser(User user) {
        final String query = "INSERT INTO User (user_id, nickname) VALUES (?, ?)";
        try (
                Connection connection = database.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getNickname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException("아이디 등록에 실패했습니다.");
        }
    }
}
