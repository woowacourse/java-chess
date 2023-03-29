package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DBConnectionTest {

    @Test
    @DisplayName("데이터베이스가 연결되는지 확인")
    void connectionTest() throws SQLException {
        try (final var connection = DBConnection.get()) {
            assertNotNull(connection);
        }
    }
}
