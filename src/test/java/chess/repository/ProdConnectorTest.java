package chess.repository;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProdConnectorTest {

    private final Connector prodConnector = new ProdConnector();

    @Test
    public void connection() {
        try (final var connection = prodConnector.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
