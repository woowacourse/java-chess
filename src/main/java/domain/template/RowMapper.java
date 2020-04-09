package domain.template;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public interface RowMapper<T> {
	T rowMap(ResultSet rs) throws SQLException;
}
