package techcourse.fp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserDao {
    private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION,
                    USERNAME,
                    PASSWORD
            );
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void insert(final User user) {
        final String query = "INSERT INTO user (user_id, name) VALUES (?, ?)";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, user.userId());
            preparedStatement.setString(2, user.name());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByUserId(final String userId) {
        final String query = "SELECT * FROM user WHERE user_id = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, userId);

            final ResultSet resultSet = preparedStatement.executeQuery();
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

    public int updateUser(final User user) {
        final String query = "UPDATE user set name = ? where user_id = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, user.name());
            preparedStatement.setString(2, user.userId());

            return preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByUserId(final String userId) {
        final String query = "DELETE FROM user WHERE user_id = ?";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, userId);

            return preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}