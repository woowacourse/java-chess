package chess.model.repository;

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
        String query = "INSERT INTO CHESS_GAME_TB(ROOM_ID, TURN_NM, BLACK_USER_NM, WHITE_USER_NM) VALUES (?, ?, ?, ?)";
        PreparedStatementSetter pss = JdbcTemplate
            .getPssFromParams(roomId, gameTurn.getName(), userNames.get(Color.BLACK),
                userNames.get(Color.WHITE));
        return jdbcTemplate.executeUpdateWithGeneratedKey(query, pss);
    }

    public void updateProceedN(int gameId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = "UPDATE CHESS_GAME_TB SET PROCEEDING_YN = 'N' WHERE ID = ?";
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, gameId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    public void updateTurn(int gameId, Color gameTurn) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = "UPDATE CHESS_GAME_TB SET TURN_NM = ? WHERE ID = ? AND PROCEEDING_YN = 'Y'";
        PreparedStatementSetter pss = pstmt -> JdbcTemplate
            .getPssFromParams(gameTurn.getName(), gameId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    public void delete(int gameId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = "DELETE FROM CHESS_GAME_TB WHERE ID = ?";
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, gameId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    // TODO ROOMID와 연동 - 조인해서 가져오기
    public Optional<Integer> getGameNumberLatest(int roomId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = "SELECT ID FROM CHESS_GAME_TB WHERE ID LIKE ? ORDER BY ID DESC";
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
        String query = "SELECT TURN_NM FROM CHESS_GAME_TB WHERE ID = ?";
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
        String query = "SELECT BLACK_USER_NM, WHITE_USER_NM FROM CHESS_GAME_TB WHERE ID = ?";
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
        String query = "SELECT PROCEEDING_YN FROM CHESS_GAME_TB WHERE ID = ?";
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
        StringBuilder query = new StringBuilder();
        query.append("UPDATE CHESS_GAME_TB");
        query.append("   SET PROCEEDING_YN = 'N'");
        query.append(" WHERE ID IN ( ");
        query.append("SELECT ID ");
        query.append("FROM ( ");
        query.append("SELECT GAME.ID ");
        query.append("  FROM CHESS_GAME_TB AS GAME ");
        query.append("  JOIN ROOM_TB AS ROOM ");
        query.append(" WHERE GAME.ROOM_ID = ROOM.ID ");
        query.append("   AND ROOM.ID = ?) AS ID_TB)");
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, roomId);
        jdbcTemplate.executeUpdate(query.toString(), pss);
    }

    public List<String> getUsers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT(BLACK_USER_NM) AS NM ");
        query.append("FROM CHESS_GAME_TB ");
        query.append("WHERE PROCEEDING_YN = 'N' ");
        query.append("UNION ALL ");
        query.append("SELECT DISTINCT(WHITE_USER_NM) ");
        query.append("FROM CHESS_GAME_TB ");
        query.append("WHERE PROCEEDING_YN = 'N' ");
        query.append("ORDER BY NM ");
        ResultSetMapper<List<String>> mapper = rs -> {
            List<String> users = new ArrayList<>();
            while (rs.next()) {
                users.add(rs.getString("NM"));
            }
            return users;
        };
        return jdbcTemplate.executeQuery(query.toString(), pstmt -> {
        }, mapper);
    }
}
