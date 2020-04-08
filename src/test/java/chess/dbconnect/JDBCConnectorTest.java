package chess.dbconnect;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

public class JDBCConnectorTest {
    @Test
    void getConnection() {
        assertThat(new JDBCConnector().getConnection()).isInstanceOf(Connection.class);
    }
}
