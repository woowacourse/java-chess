package chess.model.repository;

import static chess.model.repository.connector.ChessMySqlConnector.getConnection;

import chess.model.repository.template.JdbcTemplate;
import chess.model.repository.template.PreparedStatementSetter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RoomDao {

    private final static RoomDao INSTANCE = new RoomDao();

    private RoomDao() {
    }

    public static RoomDao getInstance() {
        return INSTANCE;
    }

    public int insert(String roomName, String roomPW) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = "INSERT INTO ROOM_TB(NM, PW) VALUES (?, ?)";
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setString(1, roomName);
            pstmt.setString(2, roomPW);
        };
        return jdbcTemplate.executeUpdateWithGeneratedKey(query, pss);
    }

    public void updateUsedN(int roomId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String query = "UPDATE ROOM_TB SET USED_YN = 'N' WHERE ID = ?";
        PreparedStatementSetter pss = pstmt -> {
            pstmt.setInt(1, roomId);
            pstmt.executeUpdate();
        };
        jdbcTemplate.executeUpdate(query, pss);
    }

    public Map<Integer, String> selectUsedOnly() throws SQLException {
        String query = "SELECT ID, NM FROM ROOM_TB WHERE USED_YN = 'Y'";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()) {
            Map<Integer, String> rooms = new HashMap<>();
            while (rs.next()) {
                rooms.put(rs.getInt("ID"), rs.getString("NM"));
            }
            return rooms;
        }
    }
}
