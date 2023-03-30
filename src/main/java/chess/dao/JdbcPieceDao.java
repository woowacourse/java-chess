package chess.dao;

import chess.domain.Color;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.dto.PieceInfoDto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPieceDao implements PieceDao {

    @Override
    public void create(int gameId, PieceInfoDto pieceInfoDto) {
        final var pieceQuery = "INSERT INTO piece VALUES(?, ?, ?, ?, ?)";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(pieceQuery)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.setString(2, pieceInfoDto.getPosition().getFile().name());
            preparedStatement.setString(3, pieceInfoDto.getPosition().getRank().name());
            preparedStatement.setString(4, pieceInfoDto.getPiece().getType().name());
            preparedStatement.setString(5, pieceInfoDto.getPiece().getColor().name());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PieceInfoDto> findAllById(int gameId) {
        List<PieceInfoDto> pieces = new ArrayList<>();

        final var pieceQuery = "SELECT * FROM piece WHERE game_id = ?";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(pieceQuery)) {
            preparedStatement.setInt(1, gameId);

            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                File file = File.valueOf(resultSet.getString("piece_file"));
                Rank rank = Rank.valueOf(resultSet.getString("piece_rank"));
                Color color = Color.valueOf(resultSet.getString("piece_team"));
                PieceType type = PieceType.valueOf(resultSet.getString("piece_type"));
                pieces.add(PieceInfoDto.create(file, rank, color, type));
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return pieces;
    }

    @Override
    public void updateById(int gameId, PieceInfoDto pieceInfoDto) {
        final var sourceQuery = "UPDATE piece SET piece_type = ?, piece_team = ? WHERE game_id = ? AND piece_file = ? AND piece_rank = ?";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(sourceQuery)) {
            preparedStatement.setString(1, pieceInfoDto.getPiece().getType().name());
            preparedStatement.setString(2, pieceInfoDto.getPiece().getColor().name());
            preparedStatement.setInt(3, gameId);
            preparedStatement.setString(4, pieceInfoDto.getPosition().getFile().name());
            preparedStatement.setString(5, pieceInfoDto.getPosition().getRank().name());

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int gameId) {
        final var pieceQuery = "DELETE FROM piece WHERE game_id = ?";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(pieceQuery)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        final var pieceQuery = "DELETE FROM piece";
        try (final var connection = ConnectionProvider.getConnection();
             final var preparedStatement = connection.prepareStatement(pieceQuery)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
