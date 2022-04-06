package chess.db.dao;

import chess.db.entity.PieceEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void save(final String position, final String symbol, final int chessGameId) {
        connection = getConnection();
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
        connection = getConnection();
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
        connection = getConnection();
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
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
