package chess.dao;

import chess.dao.connection.ConnectionGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class GameRoomDao {

    private final ConnectionGenerator connectionGenerator;

    public GameRoomDao(final ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    public Set<Integer> findExisingRoomNumbers() {
        final String query = "SELECT * FROM gameRooms";
        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            final Set<Integer> roomNumbers = new HashSet<>();

            while (resultSet.next()) {
                roomNumbers.add(resultSet.getInt("room_id"));
            }
            return roomNumbers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
