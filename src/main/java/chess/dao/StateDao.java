package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.dto.StateDto;

public class StateDao {

    private final String URL = "jdbc:mysql://localhost:3306/chess";
    private final String USER = "user";
    private final String PASSWORD = "password";

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public void updateState(final String gameState, final String color) {
        final String query = "UPDATE state SET game_state = ? , color = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, gameState);
            statement.setString(2, color);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StateDto getState() {
        final String query = "SELECT * FROM state LIMIT 1";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                return new StateDto(
                    resultSet.getString("game_state"),
                    resultSet.getString("color")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
