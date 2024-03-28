package chess.db;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class ConnectionManagerTest {
    private final ConnectionManager connectionManager = ConnectionManager.getTest();

    @Test
    void 데이터베이스_연결_시_예외가_발생하지_않는다() {
        assertThatCode(connectionManager::getConnection)
                .doesNotThrowAnyException();
    }
}
