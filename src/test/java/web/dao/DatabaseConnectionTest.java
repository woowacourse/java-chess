package web.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {
    @Test
    @DisplayName("db 연결 확인")
    public void connection() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try (Connection con = databaseConnection.getConnection()) {
            assertNotNull(con);
        }
    }
}
