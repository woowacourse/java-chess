package chess.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMapper<R> {

    R execute(Connection connection) throws SQLException;
}
