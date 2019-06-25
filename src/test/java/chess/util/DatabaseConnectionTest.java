package chess.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
    @Test
    public void connection() throws SQLException {
        try (Connection con = DatabaseConnection.getConnection()) {
            assertNotNull(con);
        }
    }
}