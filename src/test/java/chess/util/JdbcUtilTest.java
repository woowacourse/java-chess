package chess.util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JdbcUtilTest {

    @Test
    void getConnection() {
        Connection conn = JdbcUtil.getConnection();
        assertNotNull(conn);
    }
}
