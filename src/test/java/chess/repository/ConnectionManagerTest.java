package chess.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectionManagerTest {

    @DisplayName("local db와 연결을 맺는다.")
    @Test
    void makeConnection() {
        Connection connection = ConnectionManager.makeConnection();

        assertThat(connection).isNotNull();
    }
}
