package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.piece.Piece;
import model.piece.PieceGenerator;
import model.position.Position;

public class ChessBoardDao {

    private static final String TABLE = "chessboard";

    private final Connection connection;

    public ChessBoardDao(Connection connection) {
        this.connection = connection;
    }

    public void saveAll(Map<Position, Piece> board) {
        board.forEach(this::save);
    }

    public void save(Position position, Piece piece) {
        final var query = "INSERT INTO " + TABLE + " VALUES(?, ?, ?, ?)";
        try {
            final var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, position.getColumn().getValue());
            preparedStatement.setString(2, position.getRow().getValue());
            preparedStatement.setString(3, piece.getPieceType().name());
            preparedStatement.setString(4, piece.getCamp().name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Position, Piece> findAll() {
        final var query = "SELECT * FROM " + TABLE;
        try (final var preparedStatement = connection.prepareStatement(query)) {
            Map<Position, Piece> board = new HashMap<>();
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                board.put(Position.from(resultSet.getString("board_column") + resultSet.getString("board_row")),
                        PieceGenerator.getPiece(resultSet.getString("piece_type") + resultSet.getString("camp")));
            }
            return board;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Position position, Piece piece) {
        final var query =
                "UPDATE " + TABLE + " SET piece_type = ?, camp = ? WHERE board_column = ? AND board_row = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, piece.getPieceType().name());
            preparedStatement.setString(2, piece.getCamp().name());
            preparedStatement.setString(3, position.getColumn().getValue());
            preparedStatement.setString(4, position.getRow().getValue());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        final var query = "DELETE FROM " + TABLE;
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Position position) {
        final var query = "DELETE FROM " + TABLE + " WHERE board_column = ? AND board_row = ?";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, position.getColumn().getValue());
            preparedStatement.setString(2, position.getRow().getValue());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
