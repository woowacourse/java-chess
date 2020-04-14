package dao;

import org.junit.jupiter.api.BeforeEach;

class StateDaoTest {
	private StateDao stateDao;

	@BeforeEach
	void setUp() {
		stateDao = StateDao.getInstance();
	}
}