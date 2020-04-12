package domain.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public interface PreparedStatementSetter {
	void setParameters(PreparedStatement pstmt) throws SQLException;
}
