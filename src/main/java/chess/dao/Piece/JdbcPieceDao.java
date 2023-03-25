package chess.dao.Piece;

import chess.dao.JdbcConnection;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JdbcPieceDao implements PieceDao {

    private final Connection connection = JdbcConnection.getConnection();

    @Override
    public void insert(final long chessGameId, final Position position, final Piece piece) {
        final String query = "INSERT INTO piece (chess_game_id, piece_file, piece_rank, side, type) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, chessGameId);
            preparedStatement.setLong(2, position.getFileIndex());
            preparedStatement.setLong(3, position.getRankIndex());
            preparedStatement.setString(4, piece.getSide().name());
            preparedStatement.setString(5, piece.getType().name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(final long chessGameId, final Position position) {
        final String query = "DELETE FROM piece WHERE piece_file = ? AND piece_rank = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, position.getFileIndex());
            preparedStatement.setLong(2, position.getRankIndex());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Position, Piece> findAllById(final long chessGameId) {
        Map<Position, Piece> board = new HashMap<>();

        final String query = "SELECT * FROM piece WHERE chess_game_id = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, chessGameId);

            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final String type = resultSet.getString("type");
                final String side = resultSet.getString("side");
                final int file = resultSet.getInt("piece_file");
                final int rank = resultSet.getInt("piece_rank");
                board.put(Position.of(File.getFile(file), Rank.getRank(rank)), getPieceByType(type, side));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return board;
    }

    @Override
    public void deleteAll() {
        final String query = "DELETE FROM piece";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Piece getPieceByType(final String type, final String side) {
        final Type pieceType = Type.valueOf(type);
        final Side pieceSide = Side.valueOf(side);

        return PieceFactory.createPiece(pieceType, pieceSide);
    }
}
