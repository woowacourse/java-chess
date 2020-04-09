package chess.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDao extends ChessDB {

    public int insert(String roomName, String roomPW) throws SQLException {
        String query = "INSERT INTO ROOM_TB(NM, PW) VALUES (?, ?)";
        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
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
}
