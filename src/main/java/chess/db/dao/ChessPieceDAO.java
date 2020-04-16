package chess.db.dao;

import chess.db.ConnectionFactory;
import chess.db.SQLExecuteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessPieceDAO {

    public void deleteTable() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM chessPiece")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("deleteTable 오류:" + e.getMessage());
        }
    }

    public void insertPiece(String position, String chessPiece) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO chessPiece VALUES (?,?)")) {
            preparedStatement.setString(1, position);
            preparedStatement.setString(2, chessPiece);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("insertPiece 오류: " + e.getMessage());
        }
    }

    public Map<String,String> selectChessPiece() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM chessPiece");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Map<String,String> pieces = new HashMap<>();

            while (resultSet.next()){
                pieces.put(resultSet.getString("position"),resultSet.getString("chessPiece"));
            }

            return pieces;
        } catch (SQLException e) {
            throw new SQLExecuteException("selectChessPiece 오류: " + e.getMessage());
        }
    }

    public void updatePiece(String sourcePosition, String targetPosition) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE chessPiece SET position = ? WHERE position = ?")) {
            preparedStatement.setString(1, targetPosition);
            preparedStatement.setString(2, sourcePosition);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("updatePiece 오류: " + e.getMessage());
        }
    }

    public void deletePiece(String position) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE  FROM chessPiece WHERE position = ?")) {
            preparedStatement.setString(1, position);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("deletePiece 오류: " + e.getMessage());
        }
    }
}
