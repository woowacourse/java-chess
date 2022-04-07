package chess.dao;

import chess.domain.board.Board;
import chess.domain.state.GameStarted;
import chess.domain.state.GameState;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public void save(GameState gameState) {
        final Connection connection = getConnection();
        final String clearSql = "truncate table game";
        try {
            connection.prepareStatement(clearSql).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final String sql = "insert into game (state) values (?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameState.getState());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GameState findState(Board board) {
        final Connection connection = getConnection();
        final String sql = "select state from game";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return GameStarted.of(board, resultSet.getString("state"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() {
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
}
