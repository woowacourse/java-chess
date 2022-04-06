package chess.dao;

import chess.domain.ChessGame;
import chess.dto.ChessGameDto;

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

    public void save(final ChessGame chessGame) {
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
