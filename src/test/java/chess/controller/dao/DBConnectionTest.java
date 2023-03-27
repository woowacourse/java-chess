package chess.controller.dao;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DBConnectionTest {

    private final DBConnection dbConnection = new DBConnection();

    @Test
    public void connection() {
        try (final var connection = dbConnection.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}