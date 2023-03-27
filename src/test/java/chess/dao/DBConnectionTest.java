package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DBConnectionTest {

	private final DBConnection dbConnection = new DBConnection();

	@Test
	public void connection() {
		try (final var connection = dbConnection.getConnection()) {
			assertThat(connection).isNotNull();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
