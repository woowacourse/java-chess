package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectionProviderTest {

    @Test
    void getConnectionTest() {
        Connection connection = ConnectionProvider.getConnection();

        assertThat(connection).isInstanceOf(Connection.class);
    }
}
