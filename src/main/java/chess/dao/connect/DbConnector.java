package chess.dao.connect;

import java.sql.Connection;

public interface DbConnector {

    Connection getConnection();
}
