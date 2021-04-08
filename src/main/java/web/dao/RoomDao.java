package web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    private static final int NO_ROOM_NUMBER = 0;

    public int newRoomId() throws SQLException {
        int roomId = NO_ROOM_NUMBER;
        try (Connection con = databaseConnection.getConnection()) {
            String query = "INSERT INTO chessRoom() VALUES()";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();
            query = "SELECT LAST_INSERT_ID()";
            pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                roomId = rs.getInt(1);
            }
        }

        return roomId;
    }

    public List<Integer> findRoomIds() throws SQLException {
        List<Integer> roomIds = new ArrayList<>();
        try (Connection con = databaseConnection.getConnection()) {
            String query = "SELECT room_id FROM chessRoom";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                roomIds.add(rs.getInt("room_id"));
            }
        }

        return roomIds;
    }
}
