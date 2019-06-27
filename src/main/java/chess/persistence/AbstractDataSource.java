package chess.persistence;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface AbstractDataSource {
    Connection getConnection() throws SQLException;
}
