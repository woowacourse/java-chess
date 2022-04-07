package chess.dao;

import chess.util.JdbcTemplate;
import chess.util.SqlConnectionException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class DataSourceImpl implements DataSource {

    private static final String URL = "jdbc:mysql://localhost:3306/chess?autoReconnect=true";

    private Connection connection;

    public DataSourceImpl() {
        connection = JdbcTemplate.getConnection(URL);
    }

    @Override
    public Connection getConnection() {
        try {
            if (Objects.isNull(connection) || connection.isClosed()) {
                connection = JdbcTemplate.getConnection(URL);
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
