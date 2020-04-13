package chess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
    Object mapRow(final ResultSet rs) throws SQLException;
}
