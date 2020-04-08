package chess.dao;

import chess.domain.chessPiece.pieceType.PieceColor;

import java.sql.*;

public class PlayerTurnDAO {
    private Connection con = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public Connection getConnection() {
        String server = "localhost:3306";
        String database = "chessBoard";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    private void closeConnection() {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }

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
        try {
            String dropQuery = "DELETE FROM playerTurn";
            preparedStatement = getConnection().prepareStatement(dropQuery);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("deletePlayerTurn 오류:" + e.getMessage());
        } finally {
            closePrepareStatement();
            closeConnection();
        }
    }

    public void insertInitialPlayerTurn() {
        String query = "INSERT INTO playerTurn VALUES ('WHITE')";
        try {
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("initialPlayerTurn 오류: " + e.getMessage());
        } finally {
            closePrepareStatement();
            closeConnection();
        }
    }

    public String selectPlayerTurn() {
        String query = "SELECT * FROM playerTurn";
        String playerTurn = null;
        try {
            preparedStatement = getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                playerTurn = resultSet.getString("turn");
            }
        } catch (SQLException e) {
            System.err.println("selectPlayerTurn 오류: " + e.getMessage());
        } finally {
            closeResultSet();
            closePrepareStatement();
            closeConnection();
        }
        return playerTurn;
    }


    public void updatePlayerTurn(PieceColor pieceColor) {
        String query = "UPDATE playerTurn SET turn = ?";
        try {
            preparedStatement = getConnection().prepareStatement(query);
            String playerTurn = pieceColor.getColor();
            preparedStatement.setString(1, playerTurn.toUpperCase());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updatePlayerTurn 오류: " + e.getMessage());
        } finally {
            closePrepareStatement();
            closeConnection();
        }
    }
}
