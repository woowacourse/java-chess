package chess.model.repository;

import static chess.model.repository.template.JdbcTemplate.getPssFromParams;
import static chess.model.repository.template.JdbcTemplate.makeQuery;

import chess.model.repository.template.JdbcTemplate;
import chess.model.repository.template.PreparedStatementSetter;
import chess.model.repository.template.ResultSetMapper;
import java.util.HashMap;
import java.util.Map;

public class RoomDao {

    private final static RoomDao INSTANCE = new RoomDao();

    private RoomDao() {
    }

    public static RoomDao getInstance() {
        return INSTANCE;
    }

    public int insert(String roomName, String roomPW) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "INSERT INTO ROOM_TB(NM, PW)",
            "VALUES (?, ?)"
        );
        PreparedStatementSetter pss = getPssFromParams(roomName, roomPW);
        return jdbcTemplate.executeUpdateWithGeneratedKey(query, pss);
    }

    public void updateUsedN(int roomId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "UPDATE ROOM_TB",
            "SET USED_YN = 'N'",
            "WHERE ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, roomId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    public Map<Integer, String> selectUsedOnly() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "SELECT ID",
            "     , NM",
            "  FROM ROOM_TB",
            " WHERE USED_YN = 'Y'"
        );
        ResultSetMapper<Map<Integer, String>> mapper = rs -> {
            Map<Integer, String> rooms = new HashMap<>();
            while (rs.next()) {
                rooms.put(rs.getInt("ID"), rs.getString("NM"));
            }
            return rooms;
        };
        return jdbcTemplate.executeQuery(query, pstmt -> {
        }, mapper);
    }

    public Map<String, String> select(int roomId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "SELECT NM, PW, USED_YN",
            "  FROM ROOM_TB",
            " WHERE ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, roomId);
        ResultSetMapper<Map<String, String>> mapper = rs -> {
            Map<String, String> result = new HashMap<>();
            while (rs.next()) {
                result.put("NM", rs.getString("NM"));
                result.put("PW", rs.getString("PW"));
                result.put("USED_YN", rs.getString("USED_YN"));
            }
            return result;
        };
        return jdbcTemplate.executeQuery(query, pss, mapper);
    }

    public void delete(int roomId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = makeQuery(
            "DELETE FROM ROOM_TB",
            "WHERE ID = ?"
        );
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, roomId);
        jdbcTemplate.executeUpdate(query, pss);
    }
}
