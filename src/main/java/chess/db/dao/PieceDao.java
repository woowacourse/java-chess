package chess.db.dao;

import chess.db.entity.PieceEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private final DBConnector dbConnector = new DBConnector();
    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;

    public void save(final String position, final String symbol, final int chessGameId) {
        connection = dbConnector.getConnection();
        String sql = "INSERT INTO piece (position, symbol, chess_game_id) VALUES (?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setString(2, symbol);
            statement.setInt(3, chessGameId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }

    public List<PieceEntity> findByChessGameId(final int chessGameId) {
        connection = dbConnector.getConnection();
        String sql = "SELECT id, position, symbol FROM piece WHERE chess_game_id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, chessGameId);
            resultSet = statement.executeQuery();

            List<PieceEntity> pieces = new ArrayList<>();
            while (resultSet.next()) {
                pieces.add(new PieceEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("position"),
                        resultSet.getString("symbol")
                ));
            }
            return pieces;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public void delete(final int chessGameId) {
        connection = dbConnector.getConnection();
        String sql = "DELETE FROM piece WHERE chess_game_id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, chessGameId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            closeResultSet();
            closeStatement();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeResultSet() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
    }

    private void closeStatement() throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    private void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
