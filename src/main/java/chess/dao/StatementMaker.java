package chess.dao;

import java.sql.SQLException;

@FunctionalInterface
public interface StatementMaker<PreparedStatement> {
    void makeStatement(final PreparedStatement statement) throws SQLException;
}
