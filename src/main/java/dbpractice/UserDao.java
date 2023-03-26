package dbpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDao {

    private static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() { //드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addUser(final User user) {
        final var query = "INSERT INTO user VALUES(?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.userId());
            preparedStatement.setString(2, user.name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByUserId(final String userId) {
        final var query = "SELECT * FROM user WHERE user_id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("name")
                );
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public User updateUserName(final String nameToChange, final String userId) {
        final var query = "UPDATE user SET name= ? WHERE user_id = ?";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nameToChange);
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
