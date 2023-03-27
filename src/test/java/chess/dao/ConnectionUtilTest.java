package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectionUtilTest {
    @DisplayName("데이터베이스 연결 테스트")
    @Test
    void dbConnection() throws Exception {
        // given & when
        final Connection connection = ConnectionUtil.connection();

        // then
        assertThat(connection.isValid(1)).isTrue();
    }
}
