package chess.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static chess.utils.ConnectionManager.getConnection;

public abstract class JdbcTemplate {

    public void apply(String query) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.executeUpdate();
        }
    }

    public void insert(String query) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            setParameters(statement);
            statement.executeUpdate();
        }
    }

    public void insertBatch(String query) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            setParameters(statement);
            statement.executeBatch();
        }
    }

    public abstract void setParameters(PreparedStatement statement) throws SQLException;
}
