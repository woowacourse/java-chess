package chess.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
public interface RowMapper<T> {
	T mapRow(ResultSet rs) throws SQLException;
}
