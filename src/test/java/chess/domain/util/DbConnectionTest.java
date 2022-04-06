package chess.domain.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DbConnectionTest {

    @Test
    @DisplayName("mysql과 연결을 할 수 있다.")
    void connection() {
        final Connection connection = DbConnection.getConnection();

        assertThat(connection).isNotNull();
    }
}
