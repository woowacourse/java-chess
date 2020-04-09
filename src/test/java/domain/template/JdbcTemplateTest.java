package domain.template;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class JdbcTemplateTest {
	@Test
	public void connection() {
		JdbcTemplate template = new JdbcTemplate();
		Connection con = template.getConnection();
		assertNotNull(con);
	}
}
