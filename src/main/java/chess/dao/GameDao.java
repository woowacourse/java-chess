package chess.dao;

import chess.dto.TurnDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {
    private static final String URL = "jdbc:mysql://localhost:3306/chess";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public TurnDto getTurn() {
        final String sql = "SELECT game.turn "
                + "FROM game WHERE id = '1'";
        String turn = "";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                turn = resultSet.getString("turn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new TurnDto(turn);
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
