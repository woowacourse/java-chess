package chess.dao;

import chess.domain.board.GameInformation;
import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameInformationDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";
    private static final String APP_TABLE_NAME = "game_information";
    private static final String TEST_TABLE_NAME = "game_information_for_test";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<GameInformation> findAll() {
        try (final Connection connection = getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + getTableName());
            final ResultSet resultSet = statement.executeQuery();

            final List<GameInformation> gameInfos = new ArrayList<>();
            while (resultSet.next()) {
                int gameId = resultSet.getInt("game_id");
                Color color = Color.convertToColor(resultSet.getString("current_turn_color"));
                GameInformation gameInformation = new GameInformation(gameId, color);

                gameInfos.add(gameInformation);
            }
            return gameInfos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GameInformation findByGameId(int gameId) {
        try (final var connection = getConnection()) {
            String tableName = getTableName();
            final var statement = connection.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE `game_id` = ?");
            statement.setInt(1, gameId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Color color = Color.convertToColor(resultSet.getString("current_turn_color"));

                return new GameInformation(gameId, color);
            }
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void remove(int gameId) {
        try (final Connection connection = getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + getTableName() + " WHERE game_id = ?");
            statement.setInt(1, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create() {
        try (final Connection connection = getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO " + getTableName() + " (`current_turn_color`)VALUES (?)");
            statement.setString(1, Color.WHITE.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTurn(GameInformation gameInformation) {
        try (final Connection connection = getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE " + getTableName() + " SET current_turn_color = ? WHERE game_id = ?");
            statement.setString(1, gameInformation.getCurentTurnColor().name());
            statement.setInt(2, gameInformation.getGameId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GameInformation findLatestGame() {
        try (final Connection connection = getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + getTableName() + " ORDER BY game_id DESC LIMIT 1");
            final ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int gameId = resultSet.getInt("game_id");
                Color color = Color.convertToColor(resultSet.getString("current_turn_color"));
                return new GameInformation(gameId, color);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private String getTableName() {
        if (isTestEnvironment()) {
            return TEST_TABLE_NAME;
        }
        return APP_TABLE_NAME;
    }

    private boolean isTestEnvironment() {
        String testStatus = System.getProperty("TEST_ENV");
        return testStatus != null && testStatus.equalsIgnoreCase("true");
    }
}
