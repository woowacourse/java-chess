package chess.dao;

import chess.util.JdbcTemplate;
import chess.util.SqlConnectionException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceImpl implements DataSource{

    private Connection connection;

    public DataSourceImpl(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                connection = JdbcTemplate.getConnection(JdbcTemplate.URL);
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
