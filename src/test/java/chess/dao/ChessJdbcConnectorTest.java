package chess.dao;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessJdbcConnectorTest {
    @Test
    void connection() throws SQLException {
        DataSource dataSource = ChessJdbcConnector.getDataSource("chess");
        assertNotNull(dataSource.getConnection());
    }
}
