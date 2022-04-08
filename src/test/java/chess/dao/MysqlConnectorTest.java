package chess.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MysqlConnectorTest {

    @DisplayName("db 연결 테스트")
    @Test
    void connect() {
        assertDoesNotThrow(MysqlConnector::connect);
    }

    @DisplayName("db 연결종료 테스트")
    @Test
    void close() {
        Connection connection = MysqlConnector.connect();
        assertDoesNotThrow(() -> MysqlConnector.close(connection));
    }
}
