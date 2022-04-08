package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

class DatabaseConnectionTest {

	@Test
	void connection() {
		Connection connection = DatabaseConnection.getConnection();

		assertThat(connection).isNotNull();
	}
}
