package chess.dao.utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ConnectionManagerTest {

    @Test
    void createConnect() {
        assertThatThrownBy(ConnectionManager::createConnection)
            .doesNotThrowAnyException();
    }
}
