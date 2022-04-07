package chess.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DbConnectorTest {

    @Test
    @DisplayName("db 연결 확인")
    public void connectionTest() {
        DbConnector dbConnector = new DbConnector();
        assertNotNull(dbConnector.getConnection());
    }
}
