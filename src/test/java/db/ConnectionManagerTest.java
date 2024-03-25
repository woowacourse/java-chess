package db;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectionManagerTest {
    @Test
    void connectionTest() {
        ConnectionManager connectionManager = new ConnectionManager();
        try (final var connection = connectionManager.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
