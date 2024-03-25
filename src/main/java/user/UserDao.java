package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void addUser(User user) {
        try (var connection = getConnection()) {
            var statement = connection.prepareStatement("INSERT INTO user(user_id, name)VALUES( ?, ?)");
            statement.setString(1, user.userId());
            statement.setString(2, user.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAll() {
        try (var connection = getConnection()) {
            var statement = connection.prepareStatement("SELECT * FROM user");
            var resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                String userId = resultSet.getString("user_id");
                String name = resultSet.getString("name");
                users.add(new User(userId, name));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(String userId) {
        try (var connection = getConnection()) {
            var statement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            statement.setString(1, userId);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                return new User(userId, name);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) {
        try (var connection = getConnection()) {
            var statement = connection.prepareStatement("UPDATE user SET name = ? WHERE user_id = ?");
            statement.setString(1, user.name());
            statement.setString(2, user.userId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(String userId) {
        try (var connection = getConnection()) {
            var statement = connection.prepareStatement("DELETE FROM user WHERE user_id = ?");
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        try (var connection = getConnection()) {
            var statement = connection.prepareStatement("DELETE FROM user");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
