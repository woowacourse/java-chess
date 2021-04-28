package chess.dao.utils;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

class ConnectionManagerTest {

    @Test
    void createConnect() {
        assertThatCode(ConnectionManager::createConnection)
            .doesNotThrowAnyException();
    }
}
