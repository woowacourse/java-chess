package chess.dao;

import chess.dto.RoomDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private final ConnectDB connectDB;

    public RoomDAO(ConnectDB connectDB) {
        this.connectDB = connectDB;
    }

    public void createRoom(final String name) throws SQLException {
        String query = "INSERT INTO room (title, black_user, white_user, status) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
        statement.setString(1, name);
        statement.setInt(2, 1); // default black 유저
        statement.setInt(3, 2); // default white 유저
        statement.setInt(4, 1); // '진행중' 상태
        statement.executeUpdate();
    }

    public List<RoomDTO> allRooms() throws SQLException {
        String query = "SELECT room.id, room.title, black.nickname AS black_user, white.nickname AS white_user, room.status " +
                "FROM room JOIN user as black on black.id = room.black_user " +
                "JOIN user as white on white.id = room.white_user";
        PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        List<RoomDTO> rooms = new ArrayList<>();
        while (resultSet.next()) {
            boolean playing = false;
            int status = resultSet.getInt("status");
            if (status == 1) {
                playing = true;
            }
            RoomDTO roomDTO = new RoomDTO(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("black_user"),
                    resultSet.getString("white_user"),
                    status,
                    playing
            );
            rooms.add(roomDTO);
        }
        return rooms;
    }

    public void changeStatusEndByRoomId(String roomId) throws SQLException {
        String query = "UPDATE room SET status = 0 WHERE id = ?";
        PreparedStatement statement = connectDB.getConnection().prepareStatement(query);
        statement.setString(1, roomId);
        statement.executeUpdate();
    }
}
