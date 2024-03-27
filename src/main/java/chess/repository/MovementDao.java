package chess.repository;

import chess.domain.square.Movement;
import chess.infra.JdbcConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovementDao implements MovementRepository {
    private final JdbcConnectionPool connectionPool;

    public MovementDao(final JdbcConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void save(final long roomId, final Movement movement) {
        final String query = "INSERT INTO Movement (room_id, source, target) VALUES (?, ?, ?)";
        final Connection connection = connectionPool.getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, roomId);
            preparedStatement.setString(2, movement.getSource().getName());
            preparedStatement.setString(3, movement.getTarget().getName());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Movement> findAllByRoomId(final long roomId) {
        final String query = "SELECT source, target FROM Movement WHERE room_id = ? ORDER BY created_at";
        final Connection connection = connectionPool.getConnection();
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createMovements(resultSet);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private List<Movement> createMovements(final ResultSet resultSet) throws SQLException {
        List<Movement> moves = new ArrayList<>();
        while (resultSet.next()) {
            String source = resultSet.getString("source");
            String target = resultSet.getString("target");
            moves.add(Movement.from(source, target));
        }
        return moves;
    }
}
