package chess.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectorTest {
    @Test
    @DisplayName("db 연결 확인")
    public void connectionTest() {
        DBConnector dbConnector = new DBConnector();
        assertNotNull(dbConnector.getConnection());
    }
}