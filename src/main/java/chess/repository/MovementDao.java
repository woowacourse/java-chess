package chess.repository;

import chess.domain.square.Movement;
import chess.infra.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovementDao implements MovementRepository {

    @Override
    public void save(final long roomId, final Movement movement) {
        final String query = "INSERT INTO Movement (room_id, source, target) VALUES (?, ?, ?)";
        try (final Connection connection = JdbcConnection.getConnection();
             final PreparedStatement preparedStatement =
                     connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, roomId);
            preparedStatement.setString(2, movement.getSource().getName());
            preparedStatement.setString(3, movement.getTarget().getName());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Movement> findAllByRoomId(final long roomId) {
        final String query = "SELECT source, target FROM Movement WHERE room_id = ? ORDER BY created_at";
        try (final Connection connection = JdbcConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createMovements(resultSet);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
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
