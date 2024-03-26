package chess.db;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConnectionTest {
    private ConnectionGenerator connectionGenerator;

    @DisplayName("테스트 연결 테스트")
    @Test
    void testConnection() {
        connectionGenerator = new TestConnectionGenerator();
        assertThat(connectionGenerator.getConnection()).isNotNull();
    }

    @DisplayName("프로덕션 연결 테스트")
    @Test
    void productionConnection() {
        connectionGenerator = new ProductionConnectionGenerator();
        assertThat(connectionGenerator.getConnection()).isNotNull();
    }
}
