package chess.dao.jdbc;

import java.sql.Connection;

public interface DataSource {
    Connection getConnection();
}
