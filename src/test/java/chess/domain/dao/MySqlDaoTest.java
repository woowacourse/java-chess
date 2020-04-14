package chess.domain.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MySqlDaoTest {
	private MySqlDao mySqlDao;

	@BeforeEach
	void setUp() {
		mySqlDao = new MySqlDao();
	}

	@Test
	void connect() {
		Connection connection = mySqlDao.connect();
		assertThat(connection).isNotNull();
	}

	@Test
	void close() throws SQLException {
		Connection connection = mySqlDao.connect();
		mySqlDao.close(connection);
		assertThat(connection.isClosed()).isTrue();
	}
}
