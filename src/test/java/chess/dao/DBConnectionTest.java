package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DBConnectionTest {
    private final DBConnection userDao = new DBConnection();

    @Test
    public void connection() {
        try (var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
