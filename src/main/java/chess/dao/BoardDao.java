package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3307/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save(String position, int gameId) {
        Connection connection = getConnection();
        final String sql = "insert into board (position, game_id) values (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setInt(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void save(String position, String piece, String color, int gameId) {
        Connection connection = getConnection();
        final String sql = "insert into board (position, piece, color, game_id) values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setString(2, piece);
            statement.setString(3, color);
            statement.setInt(4, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String position, String piece, String color, int id) {
        Connection connection = getConnection();
        final String sql = "update board set piece = ?, color = ? where game_id = ? and position = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, piece);
            statement.setString(2, color);
            statement.setInt(3, id);
            statement.setString(4, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
