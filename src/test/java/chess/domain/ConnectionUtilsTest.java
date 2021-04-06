package chess.domain;

import java.sql.Connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectionUtilsTest {

    @DisplayName("DB 연결 테스트")
    @Test
    void getConnection() {
        Connection connection = ConnectionUtils.getConnection();

        assertThat(connection).isNotNull();
    }
}
