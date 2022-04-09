package chess.domain.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectorTest {

    private Connector connector = new Connector();

    @Test
    @DisplayName("DB 연결을 확인한다.")
    void connection() {
        assertThat(connector.makeConnection(Connector.DEV_DB_URL)).isNotNull();
    }

}
