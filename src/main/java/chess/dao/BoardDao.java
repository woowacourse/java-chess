package chess.dao;

import chess.domain.GameState;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;

public class BoardDao {

    private static final String URL = "jdbc:mysql://localhost:3308/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String INIT_BOARD_FILE_PATH = "/src/main/java/chess/dao/init-board.sql";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public int initBoard() {
        final Connection connection = getConnection();
        final ScriptRunner scriptRunner = new ScriptRunner(connection);
        final String filePath = System.getProperty("user.dir") + INIT_BOARD_FILE_PATH;
        final String sql = "SELECT id FROM board ORDER BY id DESC LIMIT 1";
        try (Reader reader = new BufferedReader(new FileReader(filePath))) {
            scriptRunner.runScript(reader);
            final PreparedStatement statement = connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return -1;
            }
            return resultSet.getInt("id");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public GameState getGameStatus(final int boardId) {
        final Connection connection = getConnection();
        final String sql = "SELECT game_status FROM board LEFT JOIN user on board.id = user.board_id WHERE user.board_id=?;";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, boardId);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            final String gameStateName = resultSet.getString("game_status");
            return GameState.valueOf(gameStateName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changeGameStatus(final String gameStatus, final int boardId) {
        final Connection connection = getConnection();
        final String sql = "UPDATE board SET game_status=? WHERE id=?;";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameStatus);
            statement.setInt(2, boardId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
