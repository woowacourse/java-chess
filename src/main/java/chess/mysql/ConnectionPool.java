package chess.mysql;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection();
}
