package chess.dao.utils;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

class JdbcConnectorTest {
    @Test
    void getConnection() {
        assertThat(JdbcConnector.getConnection()).isNotNull();
    }
}