package chess.dao.connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

class MySqlConnectionGeneratorTest {

    private final MySqlConnectionGenerator mySqlConnectionGenerator = new MySqlConnectionGenerator();

    @Test
    @DisplayName("연결 테스트")
    void connection_test() {
        assertThatNoException().isThrownBy(() -> mySqlConnectionGenerator.getConnection());
    }

}
