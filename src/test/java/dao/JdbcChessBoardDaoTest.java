package dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class JdbcChessBoardDaoTest {

    private final JdbcChessBoardDao jdbcChessBoardDao = new JdbcChessBoardDao();

    @Test
    public void connection() {
        try (final var connection = jdbcChessBoardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
