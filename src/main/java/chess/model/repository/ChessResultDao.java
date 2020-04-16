package chess.model.repository;

import static chess.model.repository.template.JdbcTemplate.getPssFromParams;
import static chess.model.repository.template.JdbcTemplate.makeQuery;

import chess.model.domain.board.TeamScore;
import chess.model.domain.piece.Color;
import chess.model.dto.GameResultDto;
import chess.model.repository.template.JdbcTemplate;
import chess.model.repository.template.PreparedStatementSetter;
import chess.model.repository.template.ResultSetMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessResultDao {

    private final static ChessResultDao INSTANCE = new ChessResultDao();

    private ChessResultDao() {
    }

    public static ChessResultDao getInstance() {
        return INSTANCE;
    }

    public void update(int gameId, TeamScore teamScore) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "UPDATE CHESS_RESULT_TB",
            "   SET BLACK_SCORE = ?",
            "     , WHITE_SCORE = ?",
            " WHERE GAME_ID = ?"
        );
        PreparedStatementSetter pss = getPssFromParams(teamScore.get(Color.BLACK),
            teamScore.get(Color.WHITE), gameId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    public void insert(int gameId, TeamScore teamScore) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "INSERT INTO CHESS_RESULT_TB(GAME_ID, BLACK_SCORE, WHITE_SCORE)",
            "VALUES (?, ?, ?)"
        );
        PreparedStatementSetter pss = getPssFromParams(gameId, teamScore.get(Color.BLACK),
            teamScore.get(Color.WHITE));
        jdbcTemplate.executeUpdate(query, pss);
    }

    public void delete(int gameId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "DELETE FROM CHESS_RESULT_TB",
            " WHERE GAME_ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, gameId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    public Map<Color, Double> select(int gameId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "SELECT BLACK_SCORE",
            "     , WHITE_SCORE",
            "  FROM CHESS_RESULT_TB",
            " WHERE GAME_ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, gameId);
        ResultSetMapper<Map<Color, Double>> mapper = rs -> {
            Map<Color, Double> selectTeamScore = new HashMap<>();
            if (!rs.next()) {
                return selectTeamScore;
            }
            selectTeamScore.put(Color.BLACK, rs.getDouble("BLACK_SCORE"));
            selectTeamScore.put(Color.WHITE, rs.getDouble("WHITE_SCORE"));
            return selectTeamScore;
        };
        return jdbcTemplate.executeQuery(query, pss, mapper);
    }

    public Optional<GameResultDto> getWinOrDraw(String userName) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "SELECT SUM(WIN) AS WIN",
            "     , SUM(DRAW) AS DRAW",
            "     , SUM(LOSE) AS LOSE",
            "  FROM (SELECT BLACK_SCORE > WHITE_SCORE AS WIN",
            "             , BLACK_SCORE = WHITE_SCORE AS DRAW",
            "             , BLACK_SCORE < WHITE_SCORE AS LOSE",
            "          FROM CHESS_RESULT_TB",
            "         WHERE GAME_ID IN(",
            "               SELECT ID",
            "                 FROM CHESS_GAME_TB",
            "                WHERE BLACK_USER_NM = ?)",
            "         UNION ALL",
            "        SELECT BLACK_SCORE < WHITE_SCORE AS WIN",
            "             , BLACK_SCORE = WHITE_SCORE AS DRAW",
            "             , BLACK_SCORE > WHITE_SCORE AS LOSE",
            "          FROM CHESS_RESULT_TB",
            "         WHERE GAME_ID IN(",
            "               SELECT ID",
            "                 FROM CHESS_GAME_TB",
            "                WHERE WHITE_USER_NM = ?)) AS TOTAL"
        );
        PreparedStatementSetter pss = getPssFromParams(userName, userName);
        ResultSetMapper<Optional<GameResultDto>> mapper = rs -> {
            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional
                .of(new GameResultDto(rs.getInt("WIN"), rs.getInt("DRAW"), rs.getInt("LOSE")));
        };
        return jdbcTemplate.executeQuery(query, pss, mapper);
    }
}
