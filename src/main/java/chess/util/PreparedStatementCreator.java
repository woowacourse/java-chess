package chess.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementCreator {

    PreparedStatement excute(Connection connection) throws SQLException;
}
