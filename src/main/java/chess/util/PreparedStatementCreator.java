package chess.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementCreator {

    PreparedStatement excute(Connection connection) throws SQLException;
}
