package chess.model.repository;

import static chess.model.repository.connector.ChessMySqlConnector.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String query = "INSERT INTO ROOM_TB(NM, PW) VALUES (?, ?)";
        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn
                .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, roomName);
            pstmt.setString(2, roomPW);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException();
            }
        }
    }

    public void updateUsedN(int roomId) throws SQLException {
        String query = "UPDATE ROOM_TB SET USED_YN = 'N' WHERE ID = ?";
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, roomId);
            pstmt.executeUpdate();
        }
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
