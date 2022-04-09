package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.DBConnector;
import chess.domain.ChessGame;

public class ChessGameDao {
    private final DBConnector dbConnector;

    public ChessGameDao() {
        this.dbConnector = new DBConnector();
    }

    public void save(String gameID, ChessGame chessGame) {
        String sql = "insert into chessGame (gameID, turn) values (?, ?)";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, gameID);
            statement.setString(2, chessGame.getTurn().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTurn(String gameID, ChessGame chessGame) {
        String sql = "update chessGame set turn = ? where gameID = ?";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, chessGame.getTurn().name());
            statement.setString(2, gameID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String findTurnByID(String gameID) {
        String sql = "select turn from chessGame where gameID = ?";
        String turn = null;
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = getPreparedStatement(gameID, sql, connection);
             ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                return null;
            }
            turn = resultSet.getString("turn");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turn;
    }

    private PreparedStatement getPreparedStatement(String gameID, String selectPiecesSql, Connection connection) throws
            SQLException {
        PreparedStatement piecesStatement = connection.prepareStatement(selectPiecesSql);
        piecesStatement.setString(1, gameID);
        return piecesStatement;
    }
}
