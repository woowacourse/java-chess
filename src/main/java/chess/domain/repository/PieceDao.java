package chess.domain.repository;

import chess.domain.repository.entity.PieceEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    public void savePieces(List<PieceEntity> pieces) {
        StringBuilder newGamePiecesSaveQuery = getBulkPiecesInsertQuery(pieces);
        try (Connection connection = ConnectionGenerator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(newGamePiecesSaveQuery.toString())) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private StringBuilder getBulkPiecesInsertQuery(List<PieceEntity> pieces) {
        StringBuilder newGamePiecesSaveQuery = new StringBuilder(
                "INSERT INTO piece (position, type, camp, board_id) VALUES");
        for (PieceEntity piece : pieces) {
            newGamePiecesSaveQuery.append(" ('")
                    .append(piece.getPosition()).append("', '")
                    .append(piece.getPieceType()).append("', '")
                    .append(piece.getCamp()).append("', ")
                    .append(piece.getBoardId()).append("),");
        }

        newGamePiecesSaveQuery.deleteCharAt(newGamePiecesSaveQuery.length() - 1);
        newGamePiecesSaveQuery.append(";");
        return newGamePiecesSaveQuery;
    }

    public List<PieceEntity> findAllPieces() {
        final var query = "SELECT * FROM piece";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PieceEntity> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(toPieceEntity(resultSet));
            }

            return results;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PieceEntity toPieceEntity(ResultSet resultSet) throws SQLException {
        return new PieceEntity(
                resultSet.getString("position"),
                resultSet.getString("type"),
                resultSet.getString("camp"),
                resultSet.getLong("board_id")
        );
    }

    public void deleteAll() {
        final var query = "DELETE FROM piece";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();

        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePiecePositionTo(String from, String to) {
        final var query = "UPDATE piece as p SET p.position = ? WHERE position = ?";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, to);
            preparedStatement.setString(2, from);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByPosition(String position) {
        String saveBoardWithDefaultQuery = "DELETE FROM piece WHERE position = ?";

        try (Connection connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(saveBoardWithDefaultQuery)) {
            preparedStatement.setString(1, position);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExistByPosition(String position) {
        String saveBoardWithDefaultQuery = "SELECT piece_id FROM piece WHERE position LIKE ?";

        try (Connection connection = ConnectionGenerator.getConnection();
             var preparedStatement = connection.prepareStatement(saveBoardWithDefaultQuery)) {
            preparedStatement.setString(1, position);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PieceEntity findByPosition(String position) {
        final var query = "SELECT * FROM piece WHERE position = ?";
        try (final var connection = ConnectionGenerator.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, position);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return toPieceEntity(resultSet);
            }

            return null;
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
