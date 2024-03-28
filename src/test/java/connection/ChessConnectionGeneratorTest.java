package connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ChessConnectionGeneratorTest {


    @DisplayName("DB에 연결한다.")
    @Test
    void connection() {
        try (final Connection connection = ChessConnectionGenerator.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
