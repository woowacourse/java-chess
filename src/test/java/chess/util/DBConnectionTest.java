package chess.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBConnectionTest {

    @Test
    @DisplayName("db connection 확인")
    void getConnection() throws SQLException {
        Connection connection = DBConnection.getConnection();
        assertNotNull(connection);
        connection.close();
    }
}
