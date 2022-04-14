package chess.web.utils;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class DBConnectorTest {

    @Test
    void connection() {
        final Connection connection = DBConnector.getConnection();
        assertThat(connection).isNotNull();
    }
}