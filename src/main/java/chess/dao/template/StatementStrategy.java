package chess.dao.template;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface StatementStrategy {
    Statement makeStatement(Connection c) throws SQLException;
}
