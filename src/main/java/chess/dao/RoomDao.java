package chess.dao;

import java.sql.*;

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
}
