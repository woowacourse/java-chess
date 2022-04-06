package chess.db.dao;

import chess.domain.ChessGame;
import chess.dto.ChessGameDto;
import chess.db.entity.ChessGameEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessGameDao {

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

    public int count() {
        connection = getConnection();
        String sql = "SELECT id FROM chess_game ORDER BY id DESC LIMIT 1";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return 0;
            }
            return resultSet.getInt("id");
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
        return 0;
    }

    public int save(final ChessGame chessGame) {
        ChessGameDto chessGameDto = ChessGameDto.from(chessGame);
        connection = getConnection();
        String sql = "INSERT INTO chess_game (state, insert_datetime, update_datetime) VALUES (?, now(), now())";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, chessGameDto.getState());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
        return count();
    }

    public ChessGameEntity find(final int id) {
        connection = getConnection();
        String sql = "SELECT id, state FROM chess_game WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new ChessGameEntity(
                    resultSet.getInt("id"),
                    resultSet.getString("state")
            );
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public void move(final int chessGameId, final String nextState) {
        connection = getConnection();
        String sql = "UPDATE chess_game SET state = ?, update_datetime = now() WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, nextState);
            statement.setInt(2, chessGameId);
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
