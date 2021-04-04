package chess.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
