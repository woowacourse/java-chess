package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    T convertToRow(ResultSet resultSet) throws SQLException;
}
