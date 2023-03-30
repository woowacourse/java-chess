package dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DBConnectionTest {
    private final DBConnection dbConnection = new DBConnection();

    @Test
    public void connection() throws SQLException {
        try (final var connection = dbConnection.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }
}
