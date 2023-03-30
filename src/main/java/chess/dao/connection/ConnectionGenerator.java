package chess.dao.connection;

import java.sql.Connection;

public interface ConnectionGenerator {
    Connection getConnection();
}
