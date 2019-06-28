package chess.dao;

import chess.application.dto.ChessLogDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessLogDao {
    private static final int MIN_RESULT = 1;
    private static final int FIRST_RESULT = 0;
    private static final int FIRST_TURN = 0;


    private JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

    private static class ChessLogDaoLazyHolder {
        private static final ChessLogDao INSTANCE = new ChessLogDao();
    }

    public static ChessLogDao getInstance() {
        return ChessLogDaoLazyHolder.INSTANCE;
    }

    public void insertChessLogByRoundId(int round, ChessLogDto chessLogDto) {
        String query = "INSERT INTO chess_log (round, turn, source, target) VALUES (?,?,?,?)";

        List<Object> queryValues = new ArrayList<>();

        queryValues.add(round);
        queryValues.add(chessLogDto.getTurn());
        queryValues.add(chessLogDto.getSource());
        queryValues.add(chessLogDto.getTarget());

        jdbcTemplate.executeUpdate(query, queryValues);
    }

    public List<ChessLogDto> selectChessLogByRoundId(int roundId) {
        String query = "SELECT turn, source, target FROM chess_log WHERE round = ?";

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

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(roundId);

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        if (results.size() < MIN_RESULT) {
            return FIRST_TURN;
        }

        Map<String, Object> result = results.get(FIRST_RESULT);
        return Integer.parseInt((String) result.get("turn"));
    }

}
