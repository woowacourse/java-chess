package chess.dao;

import chess.dto.ChessInfoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChessRoundDao {
    private static final int MIN_SIZE = 1;

    private static ChessRoundDao chessRoundDao;

    private ChessRoundDao() {
    }

    public static ChessRoundDao getInstance() {
        if (Objects.isNull(chessRoundDao)) {
            chessRoundDao = new ChessRoundDao();
        }
        return chessRoundDao;
    }

    public void insertChessInfoByRoundId(int round, ChessInfoDto chessInfoDto) {
        String query = "INSERT INTO chess_log (round, turn, source, target) VALUES (?,?,?,?)";

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Object> queryValues = new ArrayList<>();

        queryValues.add(round);
        queryValues.add(chessInfoDto.getTurn());
        queryValues.add(chessInfoDto.getSource());
        queryValues.add(chessInfoDto.getTarget());

        jdbcTemplate.executeUpdate(query, queryValues);
    }

    public List<ChessInfoDto> selectChessInfoByRoundId(int roundId) {
        String query = "SELECT turn, source, target FROM chess_log WHERE round = ?";

        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();
        List<Object> queryValues = new ArrayList<>();

        queryValues.add(roundId);

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        List<ChessInfoDto> chessInfoDtos = new ArrayList<>();

        for (Map<String, Object> result : results) {
            String turn = (String) result.get("turn");
            String source = (String) result.get("source");
            String target = (String) result.get("target");

            chessInfoDtos.add(new ChessInfoDto(turn, source, target));
        }
        return chessInfoDtos;
    }

    public int selectTurnByRoundId(int roundId) {
        String query = "SELECT turn FROM chess_log WHERE round = ? ORDER BY id DESC";
        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Object> queryValues = new ArrayList<>();
        queryValues.add(roundId);

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query, queryValues);

        if (results.size() < MIN_SIZE) {
            return 0;
        }

        Map<String, Object> result = results.get(0);
        return Integer.parseInt((String) result.get("turn"));
    }

    public int selectLastRoundId() {
        String query = "SELECT id FROM round ORDER BY id DESC LIMIT 1";
        JDBCTemplate jdbcTemplate = JDBCTemplate.getInstance();

        List<Map<String, Object>> results = jdbcTemplate.executeQuery(query);

        if (results.size() < MIN_SIZE) {
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
