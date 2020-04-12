package chess.dao;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessPiece.pieceType.PieceColor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessBoardStateDAO {

    public void deleteChessBoardState() {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM chessBoardState");
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("deletePlayerTurn 오류:" + e.getMessage());
        }
    }

    public void insertInitialChessBoardState() {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO chessBoardState(turn,caughtKing) VALUES ('WHITE' , 0)");
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("initialPlayerTurn 오류: " + e.getMessage());
        }
    }

    public String selectPlayerTurn() {
        String playerTurn = null;
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM chessBoardState");
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                playerTurn = resultSet.getString("turn");
            }
        } catch (SQLException e) {
            throw new SQLExecuteException("selectPlayerTurn 오류: " + e.getMessage());
        }
        return playerTurn;
    }

    public void updatePlayerTurn(PieceColor pieceColor) {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("UPDATE chessBoardState SET turn = ?");
        ) {
            String playerTurn = pieceColor.getColor();
            preparedStatement.setString(1, playerTurn.toUpperCase());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("updatePlayerTurn 오류: " + e.getMessage());
        }
    }

    public void updateCaughtKing(ChessBoard chessBoard) {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("UPDATE chessBoardState SET caughtKing = ?");
        ) {
            boolean caughtKing = chessBoard.isCaughtKing();
            preparedStatement.setBoolean(1, caughtKing);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLExecuteException("updatePlayerTurn 오류: " + e.getMessage());
        }
    }
}
