package chess.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface RowMapper<K, T> {
    T mapRow(ResultSet rs) throws SQLException;

    Map<K, T> mapRows(ResultSet rs) throws SQLException;
}
