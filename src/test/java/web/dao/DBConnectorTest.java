package web.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBConnectorTest {
	@DisplayName("DB 연결 테스트")
	@Test
	void connection() {
		Connection con = new DBConnector().getConnection();
		assertNotNull(con);
	}
}
