package chess.domain.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DBConnectionTest {

    private final DBConnection DBConnection = new DBConnection();

    @Test
    public void connection() throws SQLException {
        try (final var connection = DBConnection.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

}
