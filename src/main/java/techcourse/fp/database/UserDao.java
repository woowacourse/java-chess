package techcourse.fp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:13306/chess?useSSL=false&serverTimezone=UTC",
                    "root",
                    "root"
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insert(final User user) {
        final String sql = "insert into User values (?, ?)";
        try (final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.userId());
            preparedStatement.setString(2, user.userName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByUser(final String userId) {
        final String sql = "select * from user where user_id = ?";
        try (final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString("user_id"), resultSet.getString("name"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(final String userId, final String name) {
        final String sql = "update user set name = ? where user_id = ?";
        try (final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(final String userId) {
        final String sql = "delete from user where user_id = ?";
        try (final Connection connection = getConnection()) {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
