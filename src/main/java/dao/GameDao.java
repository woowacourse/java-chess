package dao;

import domain.game.TeamColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDao {
    private static final String TABLE_NAME = "game";
    private static GameDao instance = null;

    private GameDao() {
    }

    public static GameDao getInstance() {
        if (instance == null) {
            instance = new GameDao();
        }
        return instance;
    }

    Connection getConnection() {
        return DBConnector.getInstance().getConnection();
    }

    public int addGame() {
        final String query = String.format("INSERT INTO %s(turn) VALUE(?);", TABLE_NAME);
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, TeamColor.WHITE.name());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public TeamColor findTurn(int gameId) {
        final String query = String.format("SELECT turn FROM %s WHERE `gameId` = ?", TABLE_NAME);
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String turn = resultSet.getString("turn");
                return TeamColor.valueOf(turn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public TeamColor findLatestGameTurn() {
        final String query = String.format("SELECT turn FROM %s ORDER BY `gameId` DESC LIMIT 1", TABLE_NAME);
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String turn = resultSet.getString("turn");
                return TeamColor.valueOf(turn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateTurn(int gameId, TeamColor teamColor) {
        final var query = String.format("UPDATE %s SET turn = ? WHERE gameId = ?", TABLE_NAME);
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, teamColor.name());
            preparedStatement.setInt(2, gameId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeAllGames() {
        final String query = "DELETE FROM " + TABLE_NAME;
        final String resetAutoIncrementId = "ALTER TABLE " + TABLE_NAME + " auto_increment = 1";
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query);
             final PreparedStatement preparedStatementToResetId = connection.prepareStatement(resetAutoIncrementId)) {
            preparedStatement.executeUpdate();
            preparedStatementToResetId.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int tupleCount() {
        final var query = "SELECT COUNT(*) AS count FROM " + TABLE_NAME;
        try (final Connection connection = getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Integer.parseInt(resultSet.getString("count"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
