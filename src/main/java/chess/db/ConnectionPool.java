package chess.db;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection();
}
