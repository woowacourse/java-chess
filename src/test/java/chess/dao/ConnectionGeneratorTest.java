package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConnectionGeneratorTest {

    @Test
    @DisplayName("ConnectionGenerator에서 Connection을 가져오는지 확인한다.")
    void getConnection_thenReturnConnection() {
        // when
        final Connection connection = ConnectionGenerator.getConnection();

        // then
        assertThat(connection).isNotNull();
    }
}
