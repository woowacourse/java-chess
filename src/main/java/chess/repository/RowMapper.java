package chess.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {
    T map(final ResultSet resultSet) throws SQLException;
}
