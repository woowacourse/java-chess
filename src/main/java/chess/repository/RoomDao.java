package chess.repository;

import chess.database.JdbcConnectionPool;
import chess.domain.room.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDao implements RoomRepository {

    private final JdbcConnectionPool connectionPool;

    public RoomDao(final JdbcConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public long save(final Room room) {
        final String query = "INSERT INTO Room (user_id, name) VALUES (?, ?)";
        final Connection connection = connectionPool.getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setLong(1, room.getUserId());
            preparedStatement.setString(2, room.getName());
            preparedStatement.executeUpdate();
            return getGeneratedId(preparedStatement);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private long getGeneratedId(final PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        return resultSet.getLong(1);
    }

    @Override
    public List<Room> findAllByUserId(final long userId) {
        final String query = "SELECT room_id, user_id, name FROM Room WHERE user_id = ?";
        final Connection connection = connectionPool.getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createRooms(resultSet);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private List<Room> createRooms(final ResultSet resultSet) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        while (resultSet.next()) {
            rooms.add(createRoom(resultSet));
        }
        return rooms;
    }

    @Override
    public Optional<Room> findByUserIdAndName(long userId, String name) {
        final String query = "SELECT room_id, user_id, name FROM Room WHERE user_id = ? AND name = ?";
        final Connection connection = connectionPool.getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setString(2, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(createRoom(resultSet));
            }
            return Optional.empty();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Room createRoom(ResultSet resultSet) throws SQLException {
        return new Room(
                resultSet.getLong("room_id"),
                resultSet.getInt("user_id"),
                resultSet.getString("name")
        );
    }
}
