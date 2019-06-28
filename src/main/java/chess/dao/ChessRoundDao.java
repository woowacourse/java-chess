package chess.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessRoundDao {
    private static final int MIN_RESULT = 1;
    private static final int FIRST_RESULT = 0;
    private static final int FIRST_ROUND = 0;

    private JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

    private static class ChessRoundDaoLazyHolder {
        private static final ChessRoundDao INSTANCE = new ChessRoundDao();
    }

    public static ChessRoundDao getInstance() {
        return ChessRoundDaoLazyHolder.INSTANCE;
    }

    public int selectLastRoundId() {
        String query = "SELECT id FROM round ORDER BY id DESC LIMIT 1";

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query);

        if (results.size() < MIN_RESULT) {
            return FIRST_ROUND;
        }

        Map<String, Object> result = results.get(FIRST_RESULT);
        return Integer.parseInt(String.valueOf(result.get("id")));
    }

    public void insertRound(int roundId) {
        String query = "INSERT INTO round (id) VALUES (?)";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(roundId);

        jdbcTemplate.executeUpdate(query, queryValues);
    }
}
