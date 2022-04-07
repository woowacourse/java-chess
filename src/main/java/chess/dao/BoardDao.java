package chess.dao;

import chess.domain.GameState;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;

public class BoardDao {

    private static final String INIT_BOARD_FILE_PATH = "/src/main/java/chess/dao/init-board.sql";

    public int initBoard() {
        final Connection connection = CommonDao.getConnection();
        final ScriptRunner scriptRunner = new ScriptRunner(connection);
        final String filePath = System.getProperty("user.dir") + INIT_BOARD_FILE_PATH;
        final String sql = "SELECT id FROM board ORDER BY id DESC LIMIT 1";
        try (Reader reader = new BufferedReader(new FileReader(filePath))) {
            final StatementMaker<PreparedStatement> statementMaker = (statement -> {
                scriptRunner.runScript(reader);
            });
            return CommonDao.findId(sql, statementMaker, "id");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 실행할 수 없는 명령입니다.", e);
        }
    }

    public GameState getGameStatus(final int userId) {
        final Connection connection = CommonDao.getConnection();
        final String sql = "SELECT b.game_status FROM user LEFT JOIN board b on user.board_id = b.id WHERE user.id=?;";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            final String gameStateName = resultSet.getString("game_status");
            return GameState.valueOf(gameStateName);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 실행할 수 없는 명령입니다.", e);
        }
    }

    public void changeGameStatus(final String gameStatus, final int boardId) {
        final String sql = "UPDATE board SET game_status=? WHERE id=?;";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setString(1, gameStatus);
            statement.setInt(2, boardId);
            statement.execute();
        });
        CommonDao.UpdateOrDelete(sql, statementMaker);
    }

    public void deleteBoard(final int id) {
        final String sql = "DELETE FROM board WHERE id=?;";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setInt(1, id);
            statement.execute();
        });
        CommonDao.UpdateOrDelete(sql, statementMaker);
    }
}
