package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DBChessGameDaoTest {

    private final DBChessGameDao dbChessGameDao = new DBChessGameDao();

    @Test
    public void test_getConnection() {
        try (final var connection = dbChessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
