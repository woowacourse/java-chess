package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class ConnectionConfigTest {

    @Test
    public void connection() throws SQLException {
        final ConnectionConfig connectionConfig = new ConnectionConfig();
        try (final var connection = connectionConfig.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }
}
