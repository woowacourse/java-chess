package chess.dbconnction;

import chess.dao.DBCPDataSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {
    @Test
    void connection()  {
        assertDoesNotThrow(DBCPDataSource::getConnection);
    }
}