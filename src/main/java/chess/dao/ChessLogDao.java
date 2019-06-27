package chess.dao;

import chess.application.dto.ChessLogDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessLogDao {
    private static class ChessLogDaoLazyHolder {
        private static final ChessLogDao INSTANCE = new ChessLogDao();
    }

    public static ChessLogDao getInstance() {
        return ChessLogDaoLazyHolder.INSTANCE;
    }

    public void insertChessLogByRoundId(int round, ChessLogDto chessLogDto) {
        String query = "INSERT INTO chess_log (round, turn, source, target) VALUES (?,?,?,?)";

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Object> queryValues = new ArrayList<>();

        queryValues.add(round);
        queryValues.add(chessLogDto.getTurn());
        queryValues.add(chessLogDto.getSource());
        queryValues.add(chessLogDto.getTarget());

        jdbcTemplate.executeUpdate(query, queryValues);
    }

    public List<ChessLogDto> selectChessLogByRoundId(int roundId) {
        String query = "SELECT turn, source, target FROM chess_log WHERE round = ?";

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
        List<Object> queryValues = new ArrayList<>();

        queryValues.add(roundId);

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        List<ChessLogDto> chessLogDtos = new ArrayList<>();

        for (Map<String, Object> result : results) {
            String turn = (String) result.get("turn");
            String source = (String) result.get("source");
            String target = (String) result.get("target");

            chessLogDtos.add(new ChessLogDto(turn, source, target));
        }
        return chessLogDtos;
    }

    public int selectTurnByRoundId(int roundId) {
        String query = "SELECT turn FROM chess_log WHERE round = ? ORDER BY id DESC";
        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(roundId);

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        if (results.size() < 1) {
            return 0;
        }

        Map<String, Object> result = results.get(0);
        return Integer.parseInt((String) result.get("turn"));
    }

}
