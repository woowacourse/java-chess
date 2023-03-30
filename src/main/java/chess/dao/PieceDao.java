package chess.dao;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.piece.position.PiecePosition;
import chess.dto.PieceDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class PieceDao {

    private final ConnectionConfig connectionConfig = new ConnectionConfig();

    public void create(final PieceDto pieceDto) {
        final String query = "INSERT INTO piece(game_id, piece_type, file_position, rank_position, color) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pieceDto.getGameId());
            preparedStatement.setString(2, pieceDto.getType());
            preparedStatement.setString(3, pieceDto.getFile());
            preparedStatement.setInt(4, pieceDto.getRank());
            preparedStatement.setString(5, pieceDto.getColor());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> findAllIds() {
        String query = "SELECT id FROM piece";
        List<Integer> ids = new ArrayList<>();
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ids;
    }

    public Piece findPieceById(final PieceDto pieceDto) {
        String query = "SELECT * FROM piece WHERE id = ?";
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pieceDto.getId());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                final String color = resultSet.getString("color");
                final String file = resultSet.getString("file_position");
                final int rank = resultSet.getInt("rank_position");
                final Role role = Role.ofString(resultSet.getString("piece_type"));

                final BiFunction<Color, PiecePosition, Piece> pieceConstructor = role.getPieceConstructor();
                return pieceConstructor.apply(Color.ofString(color), PiecePosition.of(file.charAt(0), rank));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(final PieceDto pieceDto) {
        final String query = "UPDATE piece SET file_position = ?, rank_position = ? WHERE id = ?";
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, pieceDto.getFile());
            preparedStatement.setInt(2, pieceDto.getRank());
            preparedStatement.setInt(3, pieceDto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(final PieceDto pieceDto) {
        final String query = "DELETE FROM piece WHERE id = ?";
        try (final var connection = connectionConfig.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pieceDto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
