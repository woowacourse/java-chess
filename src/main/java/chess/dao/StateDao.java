package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chess.dto.StateDto;
import chess.exception.DataAccessException;

public class StateDao {

    private final String URL = "jdbc:mysql://localhost:3306/chess";
    private final String USER = "user";
    private final String PASSWORD = "password";
    private final DBConnection dbConnection = new DBConnection(URL, USER, PASSWORD);

    public void updateState(final String gameState) {
        final String query = "UPDATE state SET game_state = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameState);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("[DATABASE_ERROR] 체스 상태 업데이트 실패");
        }
    }

    public void updateState(final String gameState, final String color) {
        final String query = "UPDATE state SET game_state = ? , color = ?";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameState);
            preparedStatement.setString(2, color);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("[DATABASE_ERROR] 체스 상태 업데이트 실패");
        }
    }

    public StateDto getState() {
        final String query = "SELECT * FROM state LIMIT 1";
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            resultSet.next();
            return new StateDto(
                resultSet.getString("game_state"),
                resultSet.getString("color")
            );
        } catch (SQLException e) {
            throw new DataAccessException("[DATABASE_ERROR] 체스 상태 가져오기 실패");
        }
    }
}
