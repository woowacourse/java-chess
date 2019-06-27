package chess.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessRoundDao {
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

        if (results.size() < 1) {
            return 0;
        }

        Map<String, Object> result = results.get(0);
        return Integer.parseInt(String.valueOf(result.get("id")));
    }

    public void insertRound(int roundId) {
        String query = "INSERT INTO round (id) VALUES (?)";

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(roundId);

        jdbcTemplate.executeUpdate(query, queryValues);
    }
}
