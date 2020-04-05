package dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DaoTest {
	private Dao dao;

	@BeforeEach
	void setUp() {
		dao = new Dao();
	}

	@Test
	void getConnection() {
		Connection con = dao.getConnection();
		assertThat(con).isNotNull();
	}
}