package chess.service.dao;

import chess.controller.dto.RoomDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomDao {
    private static final int COLUMN_INDEX_OF_ROOM_NAME = 2;
    private static final int COLUMN_INDEX_OF_ROOM_ID = 3;
    private final Connection conn;

    public RoomDao(final Connection conn) {
        this.conn = conn;
    }

    public void save(final String roomName, final long roomId) throws SQLException {
        final String query = "INSERT INTO room_status (room_name, room_id) VALUES (?, ?)";
        try (final PreparedStatement insertQuery = conn.prepareStatement(query);) {
            insertQuery.setString(1, roomName);
            insertQuery.setLong(2, roomId);
            insertQuery.executeUpdate();
        }
    }

    public void delete(final Long roomId) throws SQLException {
        try (final Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM room_status WHERE room_id = " + roomId);
        }
    }

    public List<RoomDto> load() throws SQLException {
        try (final Statement selectQuery = conn.createStatement();
             final ResultSet rs = selectQuery.executeQuery("SELECT * FROM room_status")) {

            final List<RoomDto> list = new ArrayList<>();
            while (rs.next()) {
                list.add(makeRoomDto(rs));
            }
            Collections.reverse(list);
            return list;
        }
    }

    private RoomDto makeRoomDto(final ResultSet rs) throws SQLException {
        final Long id = rs.getLong(COLUMN_INDEX_OF_ROOM_ID);
        final String name = rs.getString(COLUMN_INDEX_OF_ROOM_NAME);
        return new RoomDto(id, name);
    }

    public String name(final Long roomId) throws SQLException {
        try (final Statement stmt = conn.createStatement();
             final ResultSet rs = stmt.executeQuery("SELECT * FROM room_status WHERE room_id = " + roomId)) {
            rs.next();
            return rs.getString(COLUMN_INDEX_OF_ROOM_NAME);
        }
    }
}
