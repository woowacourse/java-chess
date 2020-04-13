package chess.util;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JDBCConnectorTest {
	@DisplayName("JDBC Connection 테스트")
	@Test
	public void connection() {
		Connection con = JDBCConnector.getConnection();
		assertThat(con).isNotNull();
	}
}
