package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MySqlConnectorTest {

    @Test
    @DisplayName("데이터베이스에 연결한다.")
    void connection() {
        Connection connection = MySqlConnector.getConnection();

        assertThat(connection).isNotNull();
    }
}
