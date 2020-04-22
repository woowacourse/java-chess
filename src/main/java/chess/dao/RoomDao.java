package chess.dao;

import chess.domain.Turn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDao {
    public int findTurnPlayerId(int roomId) throws SQLException, ClassNotFoundException {
        String query = "select turn from room where room_id = ?";
        try (Connection con = ConnectionLoader.load();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, roomId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    throw  new IllegalArgumentException("Turn이 잘못되었습니다.");
                }
                return rs.getInt("turn");
            }
        }
    }
}
