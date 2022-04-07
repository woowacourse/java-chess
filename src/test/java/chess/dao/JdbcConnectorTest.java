package chess.dao;

import chess.util.JdbcConnector;
import java.sql.Connection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JdbcConnectorTest {

    @DisplayName("커넥터와 연결 확인")
    @Test
    void connect() {
        // given
        Connection connection = JdbcConnector.getConnection();
        // when
        Assertions.assertThat(connection).isNotNull();
        // then
    }
}
