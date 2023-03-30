package database;

import java.sql.Connection;
import java.util.List;

public interface JdbcConnector<T> {

    void executeUpdate(String query, List<String> parameters);

    T executeQuery(String query, RowMapper rowMapper);

    T executeQuery(String query, List<String> parameters, RowMapper rowMapper);

    default Connection getConnection() {
        return ConnectionFactory.getConnection();
    }
}
