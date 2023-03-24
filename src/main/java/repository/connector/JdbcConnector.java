package repository.connector;

import java.sql.Connection;

public interface JdbcConnector {
    Connection getConnection();
}
