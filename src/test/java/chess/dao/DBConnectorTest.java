package chess.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DBConnectorTest {
	private static DBConnector dbConnector;

	@BeforeAll
	public static void setup() {
		dbConnector = new DBConnector();
	}

	@Test
	public void connection() {
		Connection con = DBConnector.getConnection();
		assertNotNull(con);
	}
}