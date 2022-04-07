package chess.dao;

import java.sql.Connection;

public interface DataSource {

    Connection getConnection();

    void close();
}
