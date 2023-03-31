package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DBConnectionTest {

    @Test
    void connectionTest() {
        try (final Connection connection = DBConnection.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
