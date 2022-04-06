package chess.dao;

import chess.domain.game.Game;
import java.sql.Statement;

public class GameDao {

    private final String table;

    public GameDao(String table) {
        this.table = table;
    }

    public int saveAndGetGeneratedId() {
        final String sql = String.format("INSERT INTO %s (state) VALUES (?)", table);

        return new CommandBuilder(sql, Statement.RETURN_GENERATED_KEYS)
                .setString(GameState.RUNNING)
                .executeAndGetGeneratedKeys()
                .readFirstColumnAndClose();
    }

    public void finishGame(int gameId, Game game) {
        final String sql = String.format("UPDATE %s SET state = ? WHERE id = ?", table);
        new CommandBuilder(sql).setString(GameState.OVER)
                .setInt(gameId)
                .executeAndClose();
    }

    public boolean checkById(int gameId) {
        final String sql = String.format("SELECT COUNT(*) FROM %s WHERE id = ?", table);

        int existingGameCount = new QueryBuilder(sql).setInt(gameId)
                .execute()
                .readFirstColumnAndClose();

        return existingGameCount > 0;
    }

    public int countAll() {
        final String sql = "SELECT COUNT(*) FROM " + table;

        return new QueryBuilder(sql).execute()
                .readFirstColumnAndClose();
    }

    public int countByState(GameState state) {
        final String sql = String.format("SELECT COUNT(*) FROM %s WHERE state = ?", table);

        return new QueryBuilder(sql).setString(state)
                .execute()
                .readFirstColumnAndClose();
    }
}
