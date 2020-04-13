package chess.db.dao;

import chess.db.ConnectionFactory;
import chess.db.SQLExecuteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BlackPieceDAO implements PieceDAO {

    @Override
    public void deleteTable() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM black")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("deleteBlackTable 오류:" + e.getMessage());
        }
    }

    @Override
    public void insertPiece(String position,String chessPiece) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO black VALUES (?,?)")) {
            preparedStatement.setString(1,position);
            preparedStatement.setString(2,chessPiece);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("insertBoard 오류: " + e.getMessage());
        }
    }

    @Override
    public Map<String,String> selectBoard() {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM black");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Map<String,String> pieces = new HashMap<>();

            while (resultSet.next()){
                pieces.put(resultSet.getString("position"),resultSet.getString("chessPiece"));
            }

            return pieces;
        } catch (SQLException e) {
            throw new SQLExecuteException("selectBlackBoard 오류: " + e.getMessage());
        }
    }

    @Override
    public void updatePiece(String sourcePosition,String targetPosition) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE black SET position = ? WHERE position = ?")) {
            preparedStatement.setString(1, targetPosition);
            preparedStatement.setString(2, sourcePosition);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLExecuteException("updatePiece 오류: " + e.getMessage());
        }
    }

    @Override
    public void deleteCaughtPiece(String position) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE  FROM black WHERE position = ?")) {
            preparedStatement.setString(1, position);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("deleteCaughtPiece 오류: " + e.getMessage());
        }
    }
}
