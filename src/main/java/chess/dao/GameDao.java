package chess.dao;

import java.sql.Statement;

public class GameDao {

    private static final String PROD_TABLE_NAME = "game";

    public int saveAndGetGeneratedId() {
        final String sql = addTable("INSERT INTO %s (state) VALUES (?)");

        ResultReader reader = new StatementExecutor(sql, Statement.RETURN_GENERATED_KEYS)
                .setString(GameState.RUNNING)
                .executeCommandAndGetGeneratedKeys();
        try (reader) {
            return reader.readFirstColumn();
        }
    }

    public void finishGame(int gameId) {
        final String sql = addTable("UPDATE %s SET state = ? WHERE id = ?");
        new StatementExecutor(sql)
                .setString(GameState.OVER)
                .setInt(gameId)
                .executeCommand();
    }

    public boolean checkById(int gameId) {
        final String sql = addTable("SELECT COUNT(*) FROM %s WHERE id = ?");

        ResultReader reader = new StatementExecutor(sql)
                .setInt(gameId)
                .executeQuery();
        try (reader) {
            int existingGameCount = reader.readFirstColumn();
            return existingGameCount > 0;
        }
    }

    public int countAll() {
        final String sql = addTable("SELECT COUNT(*) FROM %s");

        ResultReader reader = new StatementExecutor(sql).executeQuery();
        try (reader) {
            return reader.readFirstColumn();
        }
    }

    public int countByState(GameState state) {
        final String sql = addTable("SELECT COUNT(*) FROM %s WHERE state = ?");

        ResultReader reader = new StatementExecutor(sql)
                .setString(state)
                .executeQuery();
        try (reader) {
            return reader.readFirstColumn();
        }
    }

    protected String addTable(String sql) {
        return String.format(sql, PROD_TABLE_NAME);
    }
}
