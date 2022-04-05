package chess.dao;

import chess.dto.GameDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public GameDto loadGame() {
        final String sql = "SELECT status.position, status.piece, game.turn "
                + "FROM status INNER JOIN game ON status.game_id = game.id WHERE game_id = '1'";
        Map<String, String> board = new HashMap<>();
        String turn = "";
        try (   Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                turn = resultSet.getString("turn");
                String position = resultSet.getString("position");
                String piece = resultSet.getString("piece");
                board.put(position, piece);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new GameDto(board, turn);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
