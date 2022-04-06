package chess.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class GameDaoTest {

	@Test
	void save() throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		connection.setAutoCommit(false);
		GameDao gameDao = new GameDao(connection);

		assertDoesNotThrow(() -> gameDao.save("start\n"));
		connection.rollback();
	}
}
