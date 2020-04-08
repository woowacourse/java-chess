package chess.database;

import java.sql.Connection;

public interface DataSource {
	Connection getConnection();

	void closeConnection(Connection connection);
}
