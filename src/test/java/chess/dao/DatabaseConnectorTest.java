package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DatabaseConnectorTest {

    @DisplayName("chess DB에 연결한다.")
    @Test
    void getConnection() {
        assertThatNoException().isThrownBy(DatabaseConnector::getConnection);
    }
}
