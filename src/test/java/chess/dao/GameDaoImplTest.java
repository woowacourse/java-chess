package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameDaoImplTest {

	private GameDaoImpl gameDaoImpl;
	private Connection connection;

	@BeforeEach
	void setUp() throws SQLException {
		connection = DatabaseConnection.getConnection();
		connection.setAutoCommit(false);
		gameDaoImpl = new GameDaoImpl(connection);
	}

	@AfterEach
	void rollback() throws SQLException {
		connection.rollback();
	}

	@Test
	void save() {
		assertDoesNotThrow(() -> gameDaoImpl.save("test", "start"));
	}

	@Test
	void update() {
		int gameId = gameDaoImpl.save("test", "start");

		assertDoesNotThrow(() -> gameDaoImpl.update(gameId, "move a2 a3"));
	}

	@Test
	void findById() {
		int gameId = gameDaoImpl.save("test", "start");

		assertThat(gameDaoImpl.findById(gameId).getCommandLog()).isEqualTo("start");
	}

	@Test
	void findAll() {
		gameDaoImpl.save("test", "start");

		assertThat(gameDaoImpl.findAll().isEmpty()).isFalse();
	}

	@Test
	void delete() {
		int gameId = gameDaoImpl.save("test", "start");
		gameDaoImpl.delete(gameId);

		assertThatThrownBy(() -> gameDaoImpl.findById(gameId))
				.isInstanceOf(NoSuchElementException.class)
				.hasMessageContaining("해당 키를 가진 데이터가 없습니다.");
	}
}
