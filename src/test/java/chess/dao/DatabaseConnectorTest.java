package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

class DatabaseConnectorTest {

    @Test
    void connection() {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connection = connector.getConnection();
        assertThat(connection).isNotNull();
    }
}