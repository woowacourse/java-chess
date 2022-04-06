package chess.dao;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
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

	@Test
	void findById() throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		connection.setAutoCommit(false);
		GameDao gameDao = new GameDao(connection);
		int gameId = gameDao.save("start\n");

		assertThat(gameDao.findById(gameId)).isEqualTo("start\n");
		connection.rollback();
	}

	@Test
	void findAll() throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		connection.setAutoCommit(false);
		GameDao gameDao = new GameDao(connection);
		gameDao.save("start\n");

		assertThat(gameDao.findAll().isEmpty()).isFalse();
		connection.rollback();
	}
}
