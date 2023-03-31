package chess.database;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class DatabaseConnectorTest {

    private final DatabaseConnector connector = new DatabaseConnector();

    @Test
    @DisplayName("데이터베이스 연결 테스트")
    void connection_test() throws SQLException {
        try (final var connection = connector.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }
}