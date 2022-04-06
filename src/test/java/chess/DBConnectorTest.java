package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class DBConnectorTest {

    @Test
    @DisplayName("데이터베이스 연결 테스트")
    void connection() {
        Connection connection = DBConnector.getConnection();

        assertThat(connection).isNotNull();
    }
}
