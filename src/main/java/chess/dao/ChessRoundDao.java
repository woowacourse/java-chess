package chess.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessRoundDao {
    private static class ChessRoundDaoLazyHolder {
        private static final ChessRoundDao INSTANCE = new ChessRoundDao();
    }

    public static ChessRoundDao getInstance() {
        return ChessRoundDaoLazyHolder.INSTANCE;
    }

    public int selectLastRoundId() {
        String query = "SELECT id FROM round ORDER BY id DESC LIMIT 1";
        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query);

        if (results.size() < 1) {
            return 0;
        }

        Map<String, Object> result = results.get(0);
        return Integer.parseInt(String.valueOf(result.get("id")));
    }

    public void insertRound(int roundId) {
        String query = "INSERT INTO round (id) VALUES (?)";
        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(roundId);

        jdbcTemplate.executeUpdate(query, queryValues);
    }
}
