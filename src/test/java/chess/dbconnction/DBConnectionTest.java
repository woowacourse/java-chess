package chess.dbconnction;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {
    @Test
    void connection() {
        DBConnection dbConnection = DBConnection.getInstance();

        Connection con = dbConnection.getConnection();
        assertNotNull(con);
    }
}