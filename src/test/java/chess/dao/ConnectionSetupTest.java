package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionSetupTest {
    private ConnectionSetup connectionSetup;

    @BeforeEach
    public void setup() {
        connectionSetup = new ConnectionSetup();
    }

    @Test
    public void connection() {
        final Connection con = ConnectionSetup.getConnection();
        assertNotNull(con);
    }
}
