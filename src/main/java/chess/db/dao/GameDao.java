package chess.db.dao;

import chess.db.entity.GameEntity;
import chess.domain.game.GameState;

public class GameDao {

    private static final String GAME_NOT_FOUND_EXCEPTION = "해당되는 ID의 게임은 존재하지 않습니다.";
    private static final String GAME_ALREADY_EXISTS_EXCEPTION = "해당되는 ID의 게임이 이미 존재합니다.";

    private final String table;

    public GameDao(String table) {
        this.table = table;
    }

    public GameEntity findById(int gameId) {
        final String sql = String.format("SELECT id, state FROM %s WHERE id = ?", table);

        final QueryReader reader = new QueryBuilder(sql).setInt(gameId)
                .execute();
        if (!reader.nextRow()) {
            throw new IllegalArgumentException(GAME_NOT_FOUND_EXCEPTION);
        }
        String state = reader.readStringAndClose("state");
        return new GameEntity(gameId, GameState.valueOf(state));
    }

    public void save(GameEntity gameEntity) {
        final String sql = String.format("INSERT INTO %s (id, state) VALUES (?, ?)", table);

        int gameId = gameEntity.getId();
        if (checkById(gameId)) {
            throw new IllegalArgumentException(GAME_ALREADY_EXISTS_EXCEPTION);
        }
        new CommandBuilder(sql).setInt(gameId)
                .setString(gameEntity.getState())
                .execute();
    }

    public void updateState(GameEntity gameEntity) {
        final String sql = String.format("UPDATE %s SET state = ? WHERE id = ?", table);
        int gameId = gameEntity.getId();

        if (!checkById(gameId)) {
            throw new IllegalArgumentException(GAME_NOT_FOUND_EXCEPTION);
        }
        new CommandBuilder(sql).setString(gameEntity.getState())
                .setInt(gameId)
                .execute();
    }

    public boolean checkById(int gameId) {
        final String sql = String.format("SELECT COUNT(*) FROM %s WHERE id = ?", table);

        int existingGameCount = new QueryBuilder(sql).setInt(gameId)
                .execute()
                .readCountResultAndClose();

        return existingGameCount > 0;
    }

    public int countAll() {
        final String sql = "SELECT COUNT(*) FROM " + table;

        return new QueryBuilder(sql).execute()
                .readCountResultAndClose();
    }

    public int countByState(GameState state) {
        final String sql = String.format("SELECT COUNT(*) FROM %s WHERE state = ?", table);

        return new QueryBuilder(sql).setString(state)
                .execute()
                .readCountResultAndClose();
    }
}
