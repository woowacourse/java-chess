package chess.infra.connection;

import java.sql.Connection;

public interface ConnectionPool {

    Connection getConnection();
}
