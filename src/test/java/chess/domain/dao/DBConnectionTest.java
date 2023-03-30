package chess.domain.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class DBConnectionTest {

    private final DBConnection dbConnection = DBConnection.getInstance();

    @Test
    public void connection() throws SQLException {
        try (final var connection = dbConnection.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

}
