package chess.db;

import java.sql.Connection;

public interface ConnectionGenerator {
    public Connection getConnection();
}
