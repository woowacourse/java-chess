package chess.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T mapRow(final ResultSet resultSet, final int rowNumber) throws SQLException;
}
