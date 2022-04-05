package chess.dao;

import java.sql.SQLException;

public interface ConnectionFunction<Connection, R> {

    R execute(Connection t) throws SQLException;
}
