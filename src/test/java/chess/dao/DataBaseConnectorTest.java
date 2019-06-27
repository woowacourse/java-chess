package chess.dao;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DataBaseConnectorTest {

    @Test
    void connection() {
        assertThat(DataBaseConnector.getConnection()).isNotNull();
    }
}
