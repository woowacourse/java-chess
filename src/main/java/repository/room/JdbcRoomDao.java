package repository.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import repository.connector.JdbcConnector;

public class JdbcRoomDao implements RoomDao {

    private final JdbcConnector connector;

    public JdbcRoomDao(JdbcConnector connector) {
        this.connector = connector;
    }

    @Override
    public long createRoom(String gameName) {
        final String query = "INSERT INTO game (gameName) VALUES (?)";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, gameName);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getLong(1);
        } catch (final SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<String> findAllRooms() {
        final String query = "SELECT gameName FROM game";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<String> gameNames = new ArrayList<>();
            while (resultSet.next()) {
                String gameName = resultSet.getString("gameName");
                gameNames.add(gameName);
            }
            return gameNames;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteAllGame() {
        final String query = "DELETE from game";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public long findGameIdByGameName(String gameName) {
        final String query = "SELECT _id FROM game WHERE gameName = ?";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, gameName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("_id");
            }
            throw new IllegalArgumentException("존재하지 않는 게임입니다.");
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteAllBoard() {
        final String query = "DELETE from board";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteAllMoveHistory() {
        final String query = "DELETE from moveHistory";
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
