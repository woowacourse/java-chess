package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserDao {

    private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = ""; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // 반복문 최적화보다 커넥션 여는거 신경쓰는게 더 의미있을 수도 !
    // 쿼리마다 커넥션을 안열고 객체에서 하나만 열어도 되나요?!
    // 멀티쓰레딩 환경에서 하나의 커넥션 사용 가능한가?
    public void insert(final User user) {
        String sql = "INSERT INTO user (user_id, name) VALUES (?, ?)";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.userId());
            preparedStatement.setString(2, user.name());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByUserId(String userId) {
        String sql = "SELECT user_id, name FROM user WHERE user_id = ?";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("user_id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new IllegalArgumentException("user not found");
    }

    public void update(User original, User modified) {
        String sql = "UPDATE user SET user_id = ?, name = ? WHERE user_id = ?";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(3, original.userId());
            preparedStatement.setString(1, modified.userId());
            preparedStatement.setString(2, modified.name());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(User user) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (final var connection = getConnection()) {
            final var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.userId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

