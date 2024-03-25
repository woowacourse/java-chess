package db;

import domain.dto.TurnDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {
    private static final String tableName = "turns";
    private final ConnectionManager connectionManager;

    private TurnDao(final ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    TurnDao() {
        this(new ConnectionManager());
    }

    TurnDto find() {
        final var query = "SELECT * FROM " + tableName;
        try (final var connection = connectionManager.getConnection();
             final var prepareStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                return new TurnDto(resultSet.getString("color"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    void update(TurnDto turnDto) {
        final String deleteQuery = "DELETE FROM " + tableName;
        final String insertQuery = "INSERT INTO " + tableName + " (color) VALUES (?)";
        try (final Connection connection = connectionManager.getConnection();
             final PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
             final PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            deleteStatement.executeUpdate();

            insertStatement.setString(1, turnDto.color());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        final String deleteQuery = "DELETE FROM " + tableName;
        try (final Connection connection = connectionManager.getConnection();
             final PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
