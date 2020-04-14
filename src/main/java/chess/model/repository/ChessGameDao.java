package chess.model.repository;

import static chess.model.repository.template.JdbcTemplate.getPssFromParams;
import static chess.model.repository.template.JdbcTemplate.makeQuery;

import chess.model.domain.piece.Color;
import chess.model.repository.template.JdbcTemplate;
import chess.model.repository.template.PreparedStatementSetter;
import chess.model.repository.template.ResultSetMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessGameDao {


    private final static ChessGameDao INSTANCE = new ChessGameDao();

    private ChessGameDao() {
    }

    public static ChessGameDao getInstance() {
        return INSTANCE;
    }

    public int insert(int roomId, Color gameTurn, Map<Color, String> userNames) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "INSERT INTO CHESS_GAME_TB(ROOM_ID, TURN_NM, BLACK_USER_NM, WHITE_USER_NM)",
            "VALUES (?, ?, ?, ?)"
        );
        PreparedStatementSetter pss = getPssFromParams(roomId, gameTurn.getName(),
            userNames.get(Color.BLACK),
            userNames.get(Color.WHITE));
        return jdbcTemplate.executeUpdateWithGeneratedKey(query, pss);
    }

    public void updateProceedN(int gameId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "UPDATE CHESS_GAME_TB",
            "   SET PROCEEDING_YN = 'N'",
            " WHERE ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, gameId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    public void updateTurn(int gameId, Color gameTurn) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "UPDATE CHESS_GAME_TB",
            "   SET TURN_NM = ?",
            " WHERE ID = ?",
            "   AND PROCEEDING_YN = 'Y'"
        );
        PreparedStatementSetter pss = getPssFromParams(gameTurn.getName(), gameId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    public void delete(int gameId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "DELETE FROM CHESS_GAME_TB",
            " WHERE ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, gameId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    public Optional<Integer> getGameNumberLatest(int roomId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "SELECT GAME.ID",
            "  FROM CHESS_GAME_TB AS GAME",
            "  JOIN ROOM_TB AS ROOM",
            " WHERE GAME.ROOM_ID = ROOM.ID",
            "   AND GAME.PROCEEDING_YN = 'Y'",
            "   AND ROOM.ID = ?",
            " ORDER BY ID DESC",
            " LIMIT 1"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, roomId);
        ResultSetMapper<Optional<Integer>> mapper = rs -> {
            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.of(rs.getInt("ID"));
        };
        return jdbcTemplate.executeQuery(query, pss, mapper);
    }

    public Optional<Color> getGameTurn(int gameId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "SELECT TURN_NM",
            "  FROM CHESS_GAME_TB",
            " WHERE ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, gameId);
        ResultSetMapper<Optional<Color>> mapper = rs -> {
            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.ofNullable(Color.of(rs.getString("TURN_NM")));
        };
        return jdbcTemplate.executeQuery(query, pss, mapper);
    }

    public Map<Color, String> getUserNames(int gameId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "SELECT BLACK_USER_NM",
            "     , WHITE_USER_NM",
            "  FROM CHESS_GAME_TB",
            " WHERE ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, gameId);
        ResultSetMapper<Map<Color, String>> mapper = rs -> {
            Map<Color, String> userNames = new HashMap<>();
            if (!rs.next()) {
                return userNames;
            }
            userNames.put(Color.BLACK, rs.getString("BLACK_USER_NM"));
            userNames.put(Color.WHITE, rs.getString("WHITE_USER_NM"));
            return Collections.unmodifiableMap(userNames);
        };
        return jdbcTemplate.executeQuery(query, pss, mapper);
    }

    public Optional<Boolean> isProceeding(int gameId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "SELECT PROCEEDING_YN",
            "  FROM CHESS_GAME_TB",
            " WHERE ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, gameId);
        ResultSetMapper<Optional<Boolean>> mapper = rs -> {
            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.of(rs.getString("PROCEEDING_YN").equalsIgnoreCase("Y"));
        };
        return jdbcTemplate.executeQuery(query, pss, mapper);
    }

    public void updateProceedNByRoomId(int roomId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "UPDATE CHESS_GAME_TB",
            "   SET PROCEEDING_YN = 'N'",
            " WHERE ID IN (",
            "       SELECT ID",
            "         FROM (",
            "              SELECT GAME.ID",
            "                FROM CHESS_GAME_TB AS GAME",
            "                JOIN ROOM_TB AS ROOM",
            "               WHERE GAME.ROOM_ID = ROOM.ID",
            "                 AND ROOM.ID = ?) AS ID_TB)"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, roomId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    public List<String> getUsers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "SELECT DISTINCT(BLACK_USER_NM) AS NM",
            "  FROM CHESS_GAME_TB",
            " WHERE PROCEEDING_YN = 'N'",
            " UNION ALL",
            "SELECT DISTINCT(WHITE_USER_NM)",
            "  FROM CHESS_GAME_TB",
            " WHERE PROCEEDING_YN = 'N'",
            " ORDER BY NM"
        );
        ResultSetMapper<List<String>> mapper = rs -> {
            List<String> users = new ArrayList<>();
            while (rs.next()) {
                users.add(rs.getString("NM"));
            }
            return users;
        };
        return jdbcTemplate.executeQuery(query, pstmt -> {
        }, mapper);
    }

    public Optional<Integer> getRoomId(int gameId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "SELECT ROOM_ID",
            "  FROM CHESS_GAME_TB",
            " WHERE ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, gameId);
        ResultSetMapper<Optional<Integer>> mapper = rs -> {
            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.of(rs.getInt("ROOM_ID"));
        };
        return jdbcTemplate.executeQuery(query, pss, mapper);
    }
}
