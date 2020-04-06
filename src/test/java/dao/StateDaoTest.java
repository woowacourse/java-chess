package dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StateDaoTest {
	private StateDao stateDao;

	@BeforeEach
	void setUp() {
		stateDao = new StateDao();
	}

	@Test
	void getConnection() {
		Connection connection = stateDao.getConnection();
		assertThat(connection).isNotNull();
	}

	@Test
	void closeConnection() throws SQLException {
		Connection connection = stateDao.getConnection();
		stateDao.closeConnection(connection);
		assertThat(connection.isClosed()).isTrue();
	}
}