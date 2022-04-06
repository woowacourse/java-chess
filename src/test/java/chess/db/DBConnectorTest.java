package chess.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBConnectorTest {

    @Test
    @DisplayName("db 연결")
    void connection() {
        Connection connection = DBConnector.getConnection();

        assertNotNull(connection);
    }
}
