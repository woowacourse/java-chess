package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class RunningGameDaoTest {

    private final RunningGameDao runningGameDao = new RunningGameDao();

    @Test
    public void connection() throws SQLException {
        try (final var connection = runningGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }
}
