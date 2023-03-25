package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DBChessGameDaoTest {

    private final DBChessGameDao dbChessGameDao = new DBChessGameDao();

    @Test
    void connectionTest() {
        try (final var connection = dbChessGameDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
