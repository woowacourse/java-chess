package chess.config;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectorTest {
    @Test
    public void 커넥션_테스트() throws SQLException {
        DbConnector dbConnector = new DbConnector(DataSource.getInstance());

        assertNotNull(dbConnector.getConnection());
    }
}