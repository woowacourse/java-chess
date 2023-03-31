package chess.dao;

import chess.dao.connection.ConnectionGenerator;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.exception.ChessDbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DbPieceDao implements PieceDao {

    private final ConnectionGenerator connectionGenerator;

    public DbPieceDao(final ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    @Override
    public Set<Piece> findAllPieceInGame(final int gameRoomId) {
        final String query = "SELECT * FROM pieces WHERE room_id = ?";
        final Set<Piece> pieces = new HashSet<>();

        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameRoomId);

            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final File file = File.valueOf(resultSet.getString("position_file"));
                final Rank rank = Rank.valueOf(resultSet.getString("position_rank"));
                final Color color = Color.valueOf(resultSet.getString("color"));
                final PieceType pieceType = PieceType.valueOf(resultSet.getString("type"));
                final Piece piece = pieceType.getInstance(Position.of(file, rank), color);

                pieces.add(piece);
            }
            return pieces;
        } catch (SQLException e) {
            throw new ChessDbException();
        }
    }

    @Override
    public void deleteAllInGame(final int gameRoomId) {
        final String query = "DELETE FROM pieces WHERE room_id = ?";

        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameRoomId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new ChessDbException();
        }
    }

    @Override
    public void updatePieces(final int gameRoomId, final Set<Piece> pieces) {
        deleteAllInGame(gameRoomId);
        final String query = "INSERT INTO pieces SET " +
                "position_file = ?, " +
                "position_rank = ?, " +
                "type = ?, " +
                "color = ?, " +
                "room_id = ?";
        try (final Connection connection = connectionGenerator.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (Piece piece : pieces) {
                preparedStatement.setString(1, piece.getPosition().getFile().name());
                preparedStatement.setString(2, piece.getPosition().getRank().name());
                preparedStatement.setString(3, PieceType.findByPiece(piece).name());
                preparedStatement.setString(4, piece.getColor().name());
                preparedStatement.setInt(5, gameRoomId);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new ChessDbException();
        }
    }
}
