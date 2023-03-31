package chess.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

class DBConnectionTest {

    @Test
    @DisplayName("Mysql 연결 테스트")
    public void testConnection() {
        assertThatNoException().isThrownBy(DBConnection::getConnection);
    }
}