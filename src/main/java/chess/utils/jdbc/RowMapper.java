package chess.utils.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> {
	T map(ResultSet resultSet) throws SQLException;
}
