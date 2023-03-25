package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DBTest {

    private final DBChessGameDao db = new DBChessGameDao();

    @Test
    public void test_getConnection() {
        try (final var connection = db.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
