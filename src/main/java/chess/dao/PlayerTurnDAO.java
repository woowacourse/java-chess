package chess.dao;

import chess.domain.chessPiece.pieceType.PieceColor;

import java.sql.*;

public class PlayerTurnDAO {

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ConnectionDAO connectionDAO = new ConnectionDAO();


    private void closeResultSet() {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            System.err.println("ResultSet 오류:" + e.getMessage());
        }
    }


    private void closePrepareStatement() {
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("preparedStatement 오류:" + e.getMessage());
        }
    }

    public void deletePlayerTurn() {
        Connection connection = connectionDAO.getConnection();
        try {
            String dropQuery = "DELETE FROM playerTurn";
            preparedStatement = connection.prepareStatement(dropQuery);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("deletePlayerTurn 오류:" + e.getMessage());
        } finally {
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
    }

    public void insertInitialPlayerTurn() {
        Connection connection = connectionDAO.getConnection();

        try {
            String query = "INSERT INTO playerTurn VALUES ('WHITE')";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("initialPlayerTurn 오류: " + e.getMessage());
        } finally {
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
    }

    public String selectPlayerTurn() {
        Connection connection = connectionDAO.getConnection();
        String playerTurn = null;

        try {
            String query = "SELECT * FROM playerTurn";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                playerTurn = resultSet.getString("turn");
            }
        } catch (SQLException e) {
            System.err.println("selectPlayerTurn 오류: " + e.getMessage());
        } finally {
            closeResultSet();
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
        return playerTurn;
    }


    public void updatePlayerTurn(PieceColor pieceColor) {
        Connection connection = connectionDAO.getConnection();

        try {
            String query = "UPDATE playerTurn SET turn = ?";
            String playerTurn = pieceColor.getColor();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, playerTurn.toUpperCase());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updatePlayerTurn 오류: " + e.getMessage());
        } finally {
            closePrepareStatement();
            connectionDAO.closeConnection();
        }
    }
}
