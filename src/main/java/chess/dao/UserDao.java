package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static final String URL = "jdbc:mysql://localhost:3308/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void createUser(final String name, final int boardId) {
        final Connection connection = getConnection();
        final String sql = "INSERT IGNORE INTO user(name, board_id) VALUES (?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, boardId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUser(final String name) {
        final Connection connection = getConnection();
        final String sql = "SELECT id, board_id FROM user WHERE name=?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return -1;
            }
            return resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getBoard(final int userId) {
        final Connection connection = getConnection();
        final String sql = "SELECT board_id FROM user WHERE id=?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return -1;
            }
            return resultSet.getInt("board_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void deleteUser(final int userId) {
        final Connection connection = getConnection();
        final String sql = "DELETE FROM user WHERE id=?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
