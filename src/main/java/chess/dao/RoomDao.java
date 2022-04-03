package chess.dao;

import chess.dto.RoomDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDao {

    public RoomDto save(final String roomName) {
        final String sql = "INSERT INTO Room (Name) VALUE (?)";

        try (final Connection connection = ConnectionGenerator.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, roomName);
            final int result = statement.executeUpdate();
            if (result == 1) {
                return RoomDto.from(roomName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
