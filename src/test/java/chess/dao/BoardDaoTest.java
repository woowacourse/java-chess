package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BoardDaoTest {
    private final BoardDao boardDao = new BoardDao();

    @Test
    public void connection() {
        try (final var connection = boardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
