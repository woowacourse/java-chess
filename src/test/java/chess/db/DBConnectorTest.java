package chess.db;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBConnectorTest {

    @Test
    @DisplayName("데이터 베이스 연결 테스트")
    void connectDB() {
        DBConnector connector = new DBConnector("chess_test");

        Assertions.assertThat(connector.getConnection()).isNotNull();
    }
}
