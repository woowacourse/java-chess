package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ConnectionManagerTest {

    @Test
    void 커넥션_연결을_확인한다() {
        final ConnectionManager connectionManager = new ConnectionManager();
        try (final Connection connection = connectionManager.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
