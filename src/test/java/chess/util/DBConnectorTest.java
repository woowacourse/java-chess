package chess.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DBConnectorTest {

    private DBConnector dbConnector;

    @BeforeEach
    void setUp() {
        dbConnector = new DBConnector();
    }

    @Test
    public void connection() {
        Connection con = dbConnector.getConnection();
        assertNotNull(con);
    }
}