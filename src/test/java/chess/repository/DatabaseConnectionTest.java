package chess.repository;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class DatabaseConnectionTest {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    @Test
    void connection() {
        try (final var connection = databaseConnection.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}