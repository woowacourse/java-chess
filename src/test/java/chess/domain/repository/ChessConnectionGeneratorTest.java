package chess.domain.repository;

import java.sql.SQLException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessConnectionGeneratorTest {

    @Test
    @DisplayName("DB 커넥션 테스트")
    void getConnectionTest() {
        try (final var connection = ChessConnectionGenerator.getConnection()) {
            AssertionsForClassTypes.assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
