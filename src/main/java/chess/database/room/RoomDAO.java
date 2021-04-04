package chess.database.room;

import com.google.gson.JsonObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static chess.controller.WebUIChessController.gson;
import static chess.database.DatabaseTransaction.closeConnection;
import static chess.database.DatabaseTransaction.getConnection;

public class RoomDAO {
    public void addRoom(Room room) throws SQLException {
        String query = "INSERT INTO room (room_id, turn, state) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE turn = VALUES(turn), state = VALUES(state)";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, room.getRoomId());
        pstmt.setString(2, room.getTurn());
        pstmt.setString(3, room.getState().toString());
        pstmt.executeUpdate();
        closeConnection(con);
    }

    public Room findByRoomId(String roomId) throws SQLException {
        String query = "SELECT * FROM room WHERE room_id = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, roomId);
        ResultSet rs = pstmt.executeQuery();
        closeConnection(con);

        return Optional.ofNullable(getRoom(rs))
                .orElseThrow(IllegalArgumentException::new);
    }

    public void validateRoomExistence(String roomId) throws SQLException {
        String query = "SELECT * FROM room WHERE room_id = ?";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, roomId);
        ResultSet rs = pstmt.executeQuery();
        closeConnection(con);

        Optional.ofNullable(getRoom(rs))
                .ifPresent(room -> {
                    throw new IllegalArgumentException();
                });
    }

    private Room getRoom(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }

        return new Room(
                rs.getString("room_id"),
                rs.getString("turn"),
                gson.fromJson(rs.getString("state"), JsonObject.class));
    }

    public List<Room> getAllRoom() throws SQLException {
        String query = "SELECT room_id FROM room";
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        closeConnection(con);

        List<Room> rooms = new ArrayList<>();
        while (rs.next()) {
            rooms.add(new Room(rs.getString("room_id"), null, null));
        }
        return rooms;
    }
}
