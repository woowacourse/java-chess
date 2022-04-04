package chess.utils;

import chess.web.utils.JdbcConnector;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class JdbcConnectorTest {

    @Test
    void getConnection() {
        final Connection connection = JdbcConnector.getConnection();
        assertThat(connection).isNotNull();
    }
}
