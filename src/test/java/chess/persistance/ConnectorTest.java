package chess.persistance;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectorTest {

    @Test
    void connect_테스트() {
        assertThat(Connector.getConnection()).isNotNull();
    }
}
