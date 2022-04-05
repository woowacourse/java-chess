package web.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class DBConnectorTest {

    @Test
    @DisplayName("jdbc 연결이 올바르게 된다.")
    void jdbcConnectTest() {
        assertThatCode(DBConnector::getConnection)
                .doesNotThrowAnyException();
    }
}
