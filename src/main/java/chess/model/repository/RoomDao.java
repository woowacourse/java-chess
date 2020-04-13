package chess.model.repository;

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
        String query = "INSERT INTO ROOM_TB(NM, PW) VALUES (?, ?)";
        PreparedStatementSetter pss = JdbcTemplate.getPssFromParams(roomName, roomPW);
        return jdbcTemplate.executeUpdateWithGeneratedKey(query, pss);
    }

    public void updateUsedN(int roomId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = "UPDATE ROOM_TB SET USED_YN = 'N' WHERE ID = ?";
        PreparedStatementSetter pss = pstmt -> pstmt.setInt(1, roomId);
        jdbcTemplate.executeUpdate(query, pss);
    }

    public Map<Integer, String> selectUsedOnly() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = "SELECT ID, NM FROM ROOM_TB WHERE USED_YN = 'Y'";
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

}
