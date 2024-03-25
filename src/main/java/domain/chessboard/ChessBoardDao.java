package domain.chessboard;

import domain.piece.Piece;
import domain.piece.PieceMaker;
import domain.square.Square;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessBoardDao {

    private final Connection connection;

    public ChessBoardDao(final Connection connection) {
        this.connection = connection;
    }

    public void addSquarePiece(final Square square, final Piece piece) {
        final var query = "INSERT INTO board VALUES(null, ?, ?, ?, ?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.file().name());
            preparedStatement.setString(2, square.rank().name());
            preparedStatement.setString(3, piece.pieceType().name());
            preparedStatement.setString(4, piece.team().name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAll(final Map<Square, Piece> pieceSquares) {
        pieceSquares.forEach((this::addSquarePiece));
    }

    public Optional<Piece> findBySquare(final Square square) {
        final var query = "SELECT * FROM board WHERE file = (?) AND `rank` = (?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.file().name());
            preparedStatement.setString(2, square.rank().name());

            final var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(PieceMaker.of(
                        resultSet.getString("piece_type"),
                        resultSet.getString("team")
                ));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public void update(final Square square, final Piece piece) {
        final var query = "UPDATE board SET piece_type = (?), team = (?) " +
                "where file = (?) AND `rank` = (?)";

        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, piece.pieceType().name());
            preparedStatement.setString(2, piece.team().name());
            preparedStatement.setString(3, square.file().name());
            preparedStatement.setString(4, square.rank().name());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBySquare(final Square square) {
        final var query = "DELETE FROM board where file = (?) AND `rank` = (?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.file().name());
            preparedStatement.setString(2, square.rank().name());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Square, Piece> findAll() {
        final var query = "SELECT * FROM board";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();

            final Map<Square, Piece> squarePieces = new HashMap<>();

            while (resultSet.next()) {
                squarePieces.put(Square.of(
                                resultSet.getString("file"),
                                resultSet.getString("rank")
                        ),
                        PieceMaker.of(
                                resultSet.getString("piece_type"),
                                resultSet.getString("team")
                        ));
            }

            return squarePieces;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isEmpty() {
        final var query = "SELECT * FROM board";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();

            return !resultSet.next();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
