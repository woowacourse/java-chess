package chess.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionManagerTest {
    @DisplayName("데이터베이스 연결 테스트")
    @Test
    void connectionTest() {
        Connection con = ConnectionManager.getConnection();
        Assertions.assertThat(con).isNotNull();
    }
}
