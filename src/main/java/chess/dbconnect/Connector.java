package chess.dbconnect;

import java.sql.Connection;

public interface Connector {
    Connection getConnection();
    void closeConnection();
}
