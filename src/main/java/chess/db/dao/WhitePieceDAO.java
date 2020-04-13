package chess.db.dao;

import chess.db.ConnectionFactory;
import chess.db.SQLExecuteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WhitePieceDAO implements PieceDAO {
    @Override
    public void deleteTable() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM white")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("deleteWhiteTable 오류:" + e.getMessage());
        }
    }

    @Override
    public void insertPiece(String piece) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO white VALUES ?")) {
            preparedStatement.setString(1, piece);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("insertBoard 오류: " + e.getMessage());
        }
    }

    @Override
    public ResultSet selectBoard() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM white");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet;
        } catch (SQLException e) {
            throw new SQLExecuteException("selectWhiteBoard 오류: " + e.getMessage());
        }
    }

    @Override
    public void updatePiece(String sourcePosition, String targetPosition) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE white SET position = ? WHERE position = ?")) {
            preparedStatement.setString(1, sourcePosition);
            preparedStatement.setString(2, targetPosition);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("updatePiece 오류: " + e.getMessage());
        }
    }

    @Override
    public void deleteCaughtPiece(String position) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE  FROM white WHERE position = ?")) {
            preparedStatement.setString(1, position);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("deleteCaughtPiece 오류: " + e.getMessage());
        }
    }
}
