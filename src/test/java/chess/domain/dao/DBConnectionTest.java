package chess.domain.dao;

import chess.dao.DBConnection;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DBConnectionTest {

    private final chess.dao.DBConnection DBConnection = new DBConnection();

    @Test
    public void connection() throws SQLException {
        try (final var connection = DBConnection.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

}
