package chess.model.repository;

import static chess.model.repository.connector.ChessMySqlConnector.getConnection;

import chess.model.repository.template.JdbcTemplate;
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
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameterUpdate(PreparedStatement pstmt) throws SQLException {
                pstmt.setString(1, roomName);
                pstmt.setString(2, roomPW);
                pstmt.executeUpdate();
            }
        };
        String query = "INSERT INTO ROOM_TB(NM, PW) VALUES (?, ?)";
        return jdbcTemplate.executeUpdateWithGeneratedKey(query);
    }

    public void updateUsedN(int roomId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate() {
            @Override
            public void setParameterUpdate(PreparedStatement pstmt) throws SQLException {
                pstmt.setInt(1, roomId);
                pstmt.executeUpdate();
            }
        };
        String query = "UPDATE ROOM_TB SET USED_YN = 'N' WHERE ID = ?";
        jdbcTemplate.executeUpdate(query);
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
