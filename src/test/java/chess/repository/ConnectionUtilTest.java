package chess.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnectionUtilTest {

    private ConnectionUtil connectionUtil;

    @BeforeEach
    void setUp() {
        connectionUtil = new ConnectionUtil();
    }

    @Test
    void getConnection() {
        // given
        Connection conn = connectionUtil.getConnection();

        // then
        assertThat(conn).isNotNull();
    }

}