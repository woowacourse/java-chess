package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DBConnectionTest {
    private final DBConnection dbConnection = new DBConnection();

    @Test
    public void connection() throws SQLException {
        try (final var connection = dbConnection.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }
}
