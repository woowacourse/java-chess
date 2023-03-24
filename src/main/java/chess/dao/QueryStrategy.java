package chess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryStrategy {

    void query(final PreparedStatement preparedStatement) throws SQLException;
}
