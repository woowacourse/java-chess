package chess.dao;

import chess.domain.board.GameInformation;
import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameInformationDao {
    private static final String TABLE_NAME = "game_information";
    private static final String UPDATE_QUERY = "UPDATE " + TABLE_NAME + " SET current_turn_color = ? WHERE game_id = ?";
    private static final String CREATE_QUERY = "INSERT INTO " + TABLE_NAME + " (`current_turn_color`)VALUES (?)";
    private static final String REMOVE_QUERY = "DELETE FROM " + TABLE_NAME + " WHERE game_id = ?";
    private static final String FIND_LATEST_QUERY = "SELECT * FROM " + TABLE_NAME + " ORDER BY game_id DESC LIMIT 1";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE `game_id` = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM " + TABLE_NAME;

    private final ConnectionGenerator connectionGenerator;

    public GameInformationDao(ConnectionGenerator connectionGenerator) {
        this.connectionGenerator = connectionGenerator;
    }

    public List<GameInformation> findAll() {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
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
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_QUERY);
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

    public GameInformation findLatestGame() {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(FIND_LATEST_QUERY);
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

    public void remove(int gameId) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(REMOVE_QUERY);
            statement.setInt(1, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create() {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(CREATE_QUERY);
            statement.setString(1, Color.WHITE.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTurn(GameInformation gameInformation) {
        try (final Connection connection = connectionGenerator.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, gameInformation.getCurentTurnColor().name());
            statement.setInt(2, gameInformation.getGameId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
