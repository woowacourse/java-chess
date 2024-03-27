package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import model.piece.Piece;
import model.position.Position;

public class ChessBoardDao {

    private final Connection connection;

    public ChessBoardDao(Connection connection) {
        this.connection = connection;
    }

    public void saveAll(Map<Position, Piece> board) {
        board.forEach(this::save);
    }

    public void save(Position position, Piece piece) {
        final var query = "INSERT INTO user VALUES(?, ?, ?, ?)";
        try {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, position.getRow().getValue());
            preparedStatement.setString(2, position.getColumn().getValue());
            preparedStatement.setString(3, position.getColumn().getValue());
            preparedStatement.setString(4, piece.getCamp().name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
