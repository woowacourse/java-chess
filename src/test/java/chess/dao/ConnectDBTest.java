package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.dao.ConnectDB.CONNECTION;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConnectDBTest {

    @Test
    @DisplayName("DB 연결 테스트")
    void connection() {
        assertNotNull(CONNECTION);
    }
}
