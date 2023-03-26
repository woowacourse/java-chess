package chess.domain.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.game.User;

public class LoginDao {

    private static final String SERVER = "localhost:13306";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    private final String database;

    public LoginDao(final Database database) {
        this.database = database.getName();
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + database + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(String id) {
        final String query = "SELECT * FROM User WHERE user_id = ?";
        try (
                Connection connection = getConnection();
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
                Connection connection = getConnection();
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
