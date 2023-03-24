package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class DbChessGameDaoTest {

    private final DbChessGameDao dbChessGameDao = new DbChessGameDao();

    @Test
    public void connection() {
        try (final var connection = dbChessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
