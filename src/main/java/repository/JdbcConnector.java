package repository;

import java.sql.Connection;

public interface JdbcConnector {
    Connection getConnection();
}
