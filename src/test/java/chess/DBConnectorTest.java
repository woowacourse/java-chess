package chess;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBConnectorTest {

    @Test
    public void connection() {
        Connection con = DBConnector.getConnection();
        assertNotNull(con);
    }

}
