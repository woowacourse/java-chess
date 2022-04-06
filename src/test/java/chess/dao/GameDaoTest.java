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

	@Test
	void update() throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		connection.setAutoCommit(false);
		GameDao gameDao = new GameDao(connection);
		int gameId = gameDao.save("start\n");
		assertDoesNotThrow(() -> gameDao.update(gameId, "move a2 a3"));
		connection.rollback();
	}
}
