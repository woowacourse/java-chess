package chess.dao;

import java.sql.Statement;

public class GameDao {

    private static final String PROD_TABLE_NAME = "game";

    private final String table;

    GameDao(String table) {
        this.table = table;
    }

    public static GameDao ofProd() {
        return new GameDao(PROD_TABLE_NAME);
    }

    public int saveAndGetGeneratedId() {
        final String sql = addTable("INSERT INTO %s (state) VALUES (?)");

        return new CommandBuilder(sql, Statement.RETURN_GENERATED_KEYS)
                .setString(GameState.RUNNING)
                .executeAndGetGeneratedKeys()
                .readFirstColumnAndClose();
    }

    public void finishGame(int gameId) {
        final String sql = addTable("UPDATE %s SET state = ? WHERE id = ?");
        new CommandBuilder(sql).setString(GameState.OVER)
                .setInt(gameId)
                .executeAndClose();
    }

    public boolean checkById(int gameId) {
        final String sql = addTable("SELECT COUNT(*) FROM %s WHERE id = ?");

        int existingGameCount = new QueryBuilder(sql).setInt(gameId)
                .execute()
                .readFirstColumnAndClose();

        return existingGameCount > 0;
    }

    public int countAll() {
        final String sql = addTable("SELECT COUNT(*) FROM %s");

        return new QueryBuilder(sql).execute()
                .readFirstColumnAndClose();
    }

    public int countByState(GameState state) {
        final String sql = addTable("SELECT COUNT(*) FROM %s WHERE state = ?");

        return new QueryBuilder(sql).setString(state)
                .execute()
                .readFirstColumnAndClose();
    }

    private String addTable(String sql) {
        return String.format(sql, table);
    }
}
