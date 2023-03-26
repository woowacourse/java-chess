package chess.repository.jdbc;

import chess.config.JdbcConnection;
import chess.domain.position.Move;
import chess.repository.MoveDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcMoveDao implements MoveDao {

    private final Connection connection;

    public JdbcMoveDao() {
        this.connection = JdbcConnection.getConnection();
    }

    @Override
    public void save(long roomId, Move move) {
        final String query = "INSERT INTO move (room_id, source, target) VALUES (?, ?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, roomId);
            preparedStatement.setString(2, move.getSource().getName());
            preparedStatement.setString(3, move.getTarget().getName());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Move> findAllByRoomId(long roomId) {
        final String query = "SELECT source, target FROM move WHERE room_id = ? ORDER BY created_at ASC";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, roomId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createMoves(resultSet);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    private List<Move> createMoves(ResultSet resultSet) throws SQLException {
        List<Move> moves = new ArrayList<>();
        while (resultSet.next()) {
            String source = resultSet.getString("source");
            String target = resultSet.getString("target");
            moves.add(Move.from(source, target));
        }
        return moves;
    }
}
