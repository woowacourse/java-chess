package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.repository.Connector;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class ConnectorTest {
    @Test
    public void connection() {
        try (final var connection = Connector.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
