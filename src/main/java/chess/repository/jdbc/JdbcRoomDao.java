package chess.repository.jdbc;

import chess.config.JdbcConnection;
import chess.domain.piece.Color;
import chess.domain.room.Room;
import chess.repository.RoomDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcRoomDao implements RoomDao {

    private final Connection connection;

    public JdbcRoomDao() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public long save(Room room) {
        final String query = "INSERT INTO room (user_id, name, winner) VALUES (?, ?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, room.getUserId());
            preparedStatement.setString(2, room.getName());
            preparedStatement.setString(3, room.getWinner().name());
            preparedStatement.executeUpdate();
            return getGeneratedId(preparedStatement);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private long getGeneratedId(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public List<Room> findAllByUserId(long userId) {
        final String query = "SELECT id, user_id, name, winner FROM room WHERE user_id = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                rooms.add(createRoom(resultSet));
            }
            return rooms;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Room findByUserIdAndName(long userId, String name) {
        final String query = "SELECT id, user_id, name, winner FROM room WHERE user_id = ? AND name = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createRoom(resultSet);
            }
            return null;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Room createRoom(ResultSet resultSet) throws SQLException {
        return new Room(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("name"),
                Color.valueOf(resultSet.getString("winner"))
        );
    }
}
