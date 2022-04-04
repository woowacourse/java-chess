package chess.dao;

import java.sql.Connection;

public interface ConnectionManager {

    Connection getConnection();

    void close(Connection connection);
}
