package chess.dao;

import chess.dto.BoardDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public BoardDto loadBoard() {
        final String sql = "SELECT board.position, board.piece, board.color "
                + "FROM board INNER JOIN game ON board.game_id = game.id WHERE game_id = '1'";
        Map<String, List<String>> board = new HashMap<>();
        try (   Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String piece = resultSet.getString("piece");
                String color = resultSet.getString("color");
                board.put(position, List.of(piece, color));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BoardDto(board);
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
