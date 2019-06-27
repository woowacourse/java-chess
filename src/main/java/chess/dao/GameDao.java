package chess.dao;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameDao {
    private static final String SELECT_TURN_BY_ID = "SELECT turn FROM game WHERE game_id = ?";
    private static final String INSERT_GAME = "INSERT INTO game(game_id) SELECT ifnull(MAX(game_id) + 1, 1) FROM game";
    private static final String UPDATE_TURN = "UPDATE game SET turn = ? WHERE game_id = ?";
    private static final String SELECT_MAX_ID = "SELECT MAX(game_id) FROM game";
    private static final String SELECT_ALL_ID = "SELECT game_id FROM game LIMIT 0, 1000";

    private static GameDao gameDao;

    private ChessJdbcTemplate chessJdbcTemplate;

    private GameDao(DataSource dataSource) {
        chessJdbcTemplate = ChessJdbcTemplate.getInstance(dataSource);
    }

    public static GameDao getInstance(DataSource dataSource) {
        if (gameDao == null) {
            gameDao = new GameDao(dataSource);
        }
        return gameDao;
    }

    public boolean findTurnByGameId(int gameId) {
        List<Object> queryValues = new ArrayList<>();
        queryValues.add(gameId);
        List<Map<String, Object>> results = chessJdbcTemplate.executeQuery(SELECT_TURN_BY_ID, queryValues);
        return (boolean) results.get(0).get("turn");
    }

    public void createNewGame() {
        chessJdbcTemplate.executeUpdate(INSERT_GAME);
    }

    public void toggleTurnById(int gameId) {
        List<Object> queryValues = Arrays.asList(!findTurnByGameId(gameId), gameId);
        chessJdbcTemplate.executeUpdate(UPDATE_TURN, queryValues);
    }

    public int findMaxId() {
        List<Map<String, Object>> results = chessJdbcTemplate.executeQuery(SELECT_MAX_ID);
        return (int) results.get(0).get("MAX(game_id)");
    }

    public List<Integer> findAllId() {
        List<Map<String, Object>> results = chessJdbcTemplate.executeQuery(SELECT_ALL_ID);
        List<Integer> allIds = new ArrayList<>();
        for (Map<String, Object> result : results) {
            allIds.add((int) result.get("game_id"));
        }
        return allIds;
    }
}
