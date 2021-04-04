package chess.dao;

import chess.controller.dto.RoomStatusDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomDao {

    private final Connection conn;

    public RoomDao(Connection conn){
        this.conn = conn;
    }

    public void save(final String roomName, final long roomId) throws SQLException {
        final String query = "INSERT INTO room_status (room_name, room_id) VALUES (?, ?)";
        final PreparedStatement insertQuery = conn.prepareStatement(query);

        insertQuery.setString(1, roomName);
        insertQuery.setLong(2, roomId);
        insertQuery.executeUpdate();
    }

    public List<RoomStatusDto> load() throws SQLException {
        final Statement selectQuery = conn.createStatement();
        final ResultSet rs = selectQuery.executeQuery("SELECT * FROM room_status");

        final List<RoomStatusDto> list = new ArrayList<>();
        while(rs.next()){
            RoomStatusDto roomStatusDto = new RoomStatusDto();
            roomStatusDto.setName(rs.getString(2));
            roomStatusDto.setId(rs.getLong(3));
            list.add(roomStatusDto);
        }
        Collections.reverse(list);
        return list;
    }

    public long id(final String roomName) throws SQLException {
        final String query = "SELECT room_id FROM room_status WHERE room_name = ?";
        final PreparedStatement insertQuery = conn.prepareStatement(query);

        insertQuery.setString(1, roomName);
        ResultSet rs = insertQuery.executeQuery();
        rs.next();

        return rs.getLong(1);
    }
}
