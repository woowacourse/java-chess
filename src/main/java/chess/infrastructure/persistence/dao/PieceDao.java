package chess.infrastructure.persistence.dao;

import chess.infrastructure.persistence.entity.PieceEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static chess.infrastructure.persistence.JdbcConnectionUtil.connection;

public class PieceDao {

    public void saveAll(final List<PieceEntity> pieceEntities) {
        final String sql = "INSERT INTO piece(id, pos_rank, pos_file, color, type, chess_game_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            Long id = getId();
            for (final PieceEntity pieceEntity : pieceEntities) {
                preparedStatement.setString(1, id.toString());
                preparedStatement.setString(2, String.valueOf(pieceEntity.rank()));
                preparedStatement.setString(3, String.valueOf(pieceEntity.file()));
                preparedStatement.setString(4, pieceEntity.color());
                preparedStatement.setString(5, pieceEntity.movementType());
                preparedStatement.setString(6, pieceEntity.chessGameId().toString());
                preparedStatement.addBatch();
                pieceEntity.setId(id);
                id++;
            }
            preparedStatement.executeBatch();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAllWithId(final List<PieceEntity> pieceEntities) {
        final String sql = "INSERT INTO piece(id, pos_rank, pos_file, color, type, chess_game_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (final PieceEntity pieceEntity : pieceEntities) {
                preparedStatement.setString(1, pieceEntity.id().toString());
                preparedStatement.setString(2, String.valueOf(pieceEntity.rank()));
                preparedStatement.setString(3, String.valueOf(pieceEntity.file()));
                preparedStatement.setString(4, pieceEntity.color());
                preparedStatement.setString(5, pieceEntity.movementType());
                preparedStatement.setString(6, pieceEntity.chessGameId().toString());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Long getId() {
        final String query = "SELECT COUNT(*) FROM piece";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException();
            }
            return resultSet.getLong(1);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PieceEntity> findByAllChessGameId(final Long chessGameId) {
        final String query = "SELECT * FROM piece where chess_game_id = ?";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, chessGameId.toString());
            final ResultSet resultSet = preparedStatement.executeQuery();
            final List<PieceEntity> pieceEntities = new ArrayList<>();
            while (resultSet.next()) {
                pieceEntities.add(new PieceEntity(
                        resultSet.getLong(1),
                        resultSet.getInt(2),
                        resultSet.getString(3).charAt(0),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getLong(6)
                ));
            }
            return pieceEntities;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllByChessGameId(final Long chessGameId) {
        final String sql = "DELETE FROM piece where chess_game_id = ?";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, chessGameId.toString());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        final String sql = "DELETE FROM piece";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
