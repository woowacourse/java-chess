package chess.db.dao;

import chess.db.entity.GameEntity;
import chess.domain.game.GameState;
import chess.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    private static final String GAME_NOT_FOUND_EXCEPTION = "해당되는 ID의 게임은 존재하지 않습니다.";
    private static final String GAME_ALREADY_EXISTS_EXCEPTION = "해당되는 ID의 게임이 이미 존재합니다.";

    private final String table;

    public GameDao(String table) {
        this.table = table;
    }

    public GameEntity findById(int gameId) {
        final String sql = "SELECT id, state FROM " + table + " WHERE id = " + gameId;

        try (final Connection connection = DatabaseUtil.getConnection()) {
            final ResultSet resultSet = DatabaseUtil.getQueryResult(sql, connection);
            if (!resultSet.next()) {
                throw new IllegalArgumentException(GAME_NOT_FOUND_EXCEPTION);
            }
            String state = resultSet.getString(2);
            return new GameEntity(gameId, GameState.valueOf(state));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException("데이터 조회 작업에 실패하였습니다.");
        }
    }

    public void save(GameEntity gameEntity) {
        int gameId = gameEntity.getId();
        if (checkById(gameId)) {
            throw new IllegalArgumentException(GAME_ALREADY_EXISTS_EXCEPTION);
        }
        final String sql = "INSERT INTO " + table + " (id, state) VALUES "
                + "(" + gameId + ", '" + gameEntity.getState() + "')";
        DatabaseUtil.executeCommand(sql);
    }

    public void updateState(GameEntity gameEntity) {
        int gameId = gameEntity.getId();
        if (!checkById(gameId)) {
            throw new IllegalArgumentException(GAME_NOT_FOUND_EXCEPTION);
        }
        final String sql = "UPDATE " + table +
                " SET state = '" + gameEntity.getState() + "' WHERE id = " + gameId;
        DatabaseUtil.executeCommand(sql);
    }

    public boolean checkById(int gameId) {
        final String sql = "SELECT COUNT(*) FROM " + table + " WHERE id = " + gameId;
        return DatabaseUtil.getCountResult(sql) > 0;
    }

    public int countAll() {
        final String sql = "SELECT COUNT(*) FROM " + table;
        return DatabaseUtil.getCountResult(sql);
    }

    public int countByState(GameState state) {
        final String sql = "SELECT COUNT(*) FROM " + table +
                " WHERE state = '" + state + "'";
        return DatabaseUtil.getCountResult(sql);
    }
}
