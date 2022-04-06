package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

class DBConnectorTest {

    @Test
    void connection() {
        final Connection connection = DBConnector.getConnection();
        assertThat(connection).isNotNull();
    }
}
