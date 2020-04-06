package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserDaoTest {
	private final UserDao userDao = new UserDao(new RepositoryConnector());

	@BeforeEach
	void setUp() throws SQLException {
		userDao.deleteByName("siyoung");
	}

	@Test
	void crud() throws SQLException {
		userDao.addUser(new User("kyle"));
		userDao.insertIfNotExists(new User("kyle"));
		userDao.insertIfNotExists(new User("pobi"));
		userDao.deleteByName("pobi");
		userDao.updateByName("kyle", new User("siyoung"));

		assertThat(userDao.findByUserName("siyoung")).isEqualTo(new User("siyoung"));
	}
}