package chess.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {
	@Test
	void 데이터베이스_연결_확인() throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		assertNotNull(connection);
	}
}
