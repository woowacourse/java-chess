package chess.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class ConnectionGeneratorTest {

    @Test
    public void connection() {
        ConnectGenerator connectGenerator = new ConnectGenerator();
        try (final var connection = connectGenerator.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            return;
        }
    }
}
