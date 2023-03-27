package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

class ConnectionGeneratorTest {

    private final ConnectionGenerator connectionGenerator = new ConnectionGenerator();

    @Test
    @DisplayName("연결 테스트")
    void connection_test() {
        assertThatNoException().isThrownBy(() -> connectionGenerator.getConnection());
    }

}
