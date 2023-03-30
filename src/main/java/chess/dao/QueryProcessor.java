package chess.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryProcessor {
    void process(PreparedStatement preparedStatement) throws SQLException;

}
