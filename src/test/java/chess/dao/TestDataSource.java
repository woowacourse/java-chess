package chess.dao;

import chess.util.JdbcTemplate;
import chess.util.JdbcTestFixture;
import chess.util.SqlConnectionException;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDataSource implements DataSource {

    private Connection connection;

    public TestDataSource(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                connection = JdbcTemplate.getConnection(JdbcTestFixture.DEV_URL);
            }
        } catch (SQLException e) {
            throw new SqlConnectionException(SqlConnectionException.MESSAGE, e);
        }
        return connection;
    }

    @Override
    public void close() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new SqlConnectionException(SqlConnectionException.MESSAGE, e);
        }
    }
}
