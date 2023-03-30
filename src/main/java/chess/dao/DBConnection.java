package chess.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection extends AutoCloseable {
    Connection getConnection();

    @Override
    void close() throws SQLException;
}
