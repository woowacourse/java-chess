package chess.dao.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementStrategy  {
    PreparedStatement makeStatement(Connection c) throws SQLException;
}
