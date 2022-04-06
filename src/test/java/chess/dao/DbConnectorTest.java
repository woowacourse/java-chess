package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

class DbConnectorTest {

    @Test
    void connection() {
        final Connection connection = DbConnector.getConnection();
        assertThat(connection).isNotNull();
    }
}
