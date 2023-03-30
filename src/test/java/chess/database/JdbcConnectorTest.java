package chess.database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class JdbcConnectorTest {

    @Test
    void getConnection() {
        assertDoesNotThrow(() -> new JdbcConnector().getConnection());
    }
}
