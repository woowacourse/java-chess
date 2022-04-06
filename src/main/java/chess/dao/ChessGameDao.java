package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.domain.ChessGame;
import chess.domain.piece.Color;

public class ChessGameDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private Connection getConnection() {
        loadDriver();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void save(ChessGame game, int board_id) {
        final Connection connection = getConnection();
        final String sql = "insert into chessGame (board_id,turn) values (?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, board_id);
            statement.setString(2, game.getTurn());
            statement.executeUpdate();
            new BoardDao().save(game.getBoard(), board_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChessGame findById(int board_id) {
        final Connection connection = getConnection();
        final String sql = "select board_id, turn from chessGame where board_id = ?";
        ChessGame chessGame = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, board_id);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            chessGame = new ChessGame(
                new BoardDao().find(board_id),
                Color.getColor(resultSet.getString("turn"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chessGame;
    }
}
