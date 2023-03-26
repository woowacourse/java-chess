package chessgame.dao;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class DatabaseConnectionTest {

    @Test
    void getConnection() throws SQLException {
        final DatabaseConnection databaseConnection = new DatabaseConnection();

        try (final var connection = databaseConnection.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }
}
