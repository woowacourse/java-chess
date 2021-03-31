package chess.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnectDBTest {
    private ConnectDB connectDB;

    @BeforeEach
    void setUp() {
        connectDB = new ConnectDB();
    }

    @Test
    void connection() {
        Connection con = connectDB.getConnection();
        assertNotNull(con);
    }

}