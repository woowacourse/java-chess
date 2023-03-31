package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectionPoolTest {
    private final ConnectionPool connectionPool = new ConnectionPool();

    @Test
    public void connection() {
        try (final var connection = connectionPool.getDatabaseConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}