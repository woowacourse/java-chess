package db;

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

    public TurnDao() {
        this(new ConnectionManager());
    }

    public String find() {
        final var query = "SELECT * FROM " + tableName;
        try (final var connection = connectionManager.getConnection();
             final var prepareStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("color");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(String color) {
        final String deleteQuery = "DELETE FROM " + tableName;
        final String insertQuery = "INSERT INTO " + tableName + " (color) VALUES (?)";
        try (final Connection connection = connectionManager.getConnection();
             final PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
             final PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            deleteStatement.executeUpdate();

            insertStatement.setString(1, color);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
