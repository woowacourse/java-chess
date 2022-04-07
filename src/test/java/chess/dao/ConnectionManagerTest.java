package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConnectionManagerTest {

    @DisplayName("DB와 연결이 되는지 확인한다.")
    @Test
    void getConnection() {
        assertThat(ConnectionManager.getConnection()).isNotNull();
    }
}
