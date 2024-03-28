package repository;

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

    public void addSquarePiece(final Square square, final Piece piece, final int gameId) {
        final var query = "INSERT INTO board (file, `rank`, piece_type, team, game_id) VALUES (?, ?, ?, ?, ?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, square.file().name());
            preparedStatement.setString(2, square.rank().name());
            preparedStatement.setString(3, piece.pieceType().name());
            preparedStatement.setString(4, piece.team().name());
            preparedStatement.setInt(5, gameId);

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAll(final Map<Square, Piece> pieceSquares, final int gameId) {
        pieceSquares.forEach((square, piece) -> addSquarePiece(square, piece, gameId));
    }

    public Optional<Piece> findBySquare(final Square square, final int gameId) {
        final var query = "SELECT * FROM board WHERE file = (?) AND `rank` = (?) AND game_id = (?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.file().name());
            preparedStatement.setString(2, square.rank().name());
            preparedStatement.setInt(3, gameId);

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

    public Map<Square, Piece> findAll(final int gameId) {
        final var query = "SELECT * FROM board WHERE game_id = (?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);

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

    public void update(final Square square, final Piece piece, final int gameId) {
        final var query = "UPDATE board SET piece_type = (?), team = (?) " +
                "where file = (?) AND `rank` = (?) AND game_id = (?)";

        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, piece.pieceType().name());
            preparedStatement.setString(2, piece.team().name());
            preparedStatement.setString(3, square.file().name());
            preparedStatement.setString(4, square.rank().name());
            preparedStatement.setInt(5, gameId);

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBySquare(final Square square, final int gameId) {
        final var query = "DELETE FROM board where file = (?) AND `rank` = (?) AND game_id = (?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, square.file().name());
            preparedStatement.setString(2, square.rank().name());
            preparedStatement.setInt(3, gameId);

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isEmpty(final int gameId) {
        final var query = "SELECT * FROM board WHERE game_id = (?)";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);

            final var resultSet = preparedStatement.executeQuery();

            return !resultSet.next();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countKing(final int gameId) {
        final var query = "SELECT count(id) AS 'king_count' FROM board WHERE game_id = (?) AND piece_type = 'KING'";
        try (final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);

            final var resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return resultSet.getInt("king_count");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
