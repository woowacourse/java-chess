package chess.dao;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class GameDaoImplTest {

	@Test
	void save() throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		connection.setAutoCommit(false);
		GameDaoImpl gameDaoImpl = new GameDaoImpl(connection);

		assertDoesNotThrow(() -> gameDaoImpl.save("test", "start"));
		connection.rollback();
	}

	@Test
	void update() throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		connection.setAutoCommit(false);
		GameDaoImpl gameDaoImpl = new GameDaoImpl(connection);
		int gameId = gameDaoImpl.save("test", "start");

		assertDoesNotThrow(() -> gameDaoImpl.update(gameId, "move a2 a3"));
		connection.rollback();
	}

	@Test
	void findById() throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		connection.setAutoCommit(false);
		GameDaoImpl gameDaoImpl = new GameDaoImpl(connection);
		int gameId = gameDaoImpl.save("test", "start");

		assertThat(gameDaoImpl.findById(gameId).getCommandLog()).isEqualTo("start");
		connection.rollback();
	}

	@Test
	void findAll() throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		connection.setAutoCommit(false);
		GameDaoImpl gameDaoImpl = new GameDaoImpl(connection);
		gameDaoImpl.save("test", "start");

		assertThat(gameDaoImpl.findAll().isEmpty()).isFalse();
		connection.rollback();
	}
}
