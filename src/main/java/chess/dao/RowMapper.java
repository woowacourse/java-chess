package chess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {

    T convertToRow(final ResultSet resultSet) throws SQLException;
}
