package chess.dao;

import chess.domain.game.state.ChessGame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {
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

    public void save(ChessGame chessGame) {
        Connection connection = getConnection();
        final String sql = "insert into game (state) values (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chessGame.getClass().getSimpleName().toLowerCase());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int findRecentGame() {
        final Connection connection = getConnection();
        final String sql = "select id from game order by id desc limit 1";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return 0;
            }
            return resultSet.getInt("id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void update(int id, ChessGame chessGame) {
        Connection connection = getConnection();
        final String sql = "update game set state = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chessGame.getClass().getSimpleName().toLowerCase());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String findById(int id) {
        final Connection connection = getConnection();
        final String sql = "select state from game where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return resultSet.getString("state");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
