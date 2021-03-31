package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConnectDBTest {
    private ConnectDB connectDB;

    @BeforeEach
    void setUp() {
        connectDB = new ConnectDB();
    }

    @Test
    @DisplayName("DB 연결 테스트")
    void connection() {
        Connection con = connectDB.getConnection();
        assertNotNull(con);
    }
}
