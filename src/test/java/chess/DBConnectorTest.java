package chess;

import chess.dao.TestDBConnector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class DBConnectorTest {

    @Test
    @DisplayName("프로덕션 데이터베이스 연결 테스트")
    void DBConnection() {
        Connection connection = DBConnector.getConnection();

        assertThat(connection).isNotNull();
    }

    @Test
    @DisplayName("테스트 데이터베이스 연결 테스트")
    void testDBConnection() {
        TestDBConnector testDBConnector = new TestDBConnector();
        Connection connection = testDBConnector.getConnection();

        assertThat(connection).isNotNull();
    }
}
