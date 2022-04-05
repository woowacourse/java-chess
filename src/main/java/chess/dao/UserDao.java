package chess.dao;

import chess.Controller.dto.UserDto;
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

    public UserDto getUser(final String name) {
        final Connection connection = getConnection();
        final String sql = "SELECT id, board_id FROM user WHERE name=?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return UserDto.fromEntity(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
