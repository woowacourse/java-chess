package chess.web.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {

	void setArgument(PreparedStatement preparedStatement) throws SQLException;

}
