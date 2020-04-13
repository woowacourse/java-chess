package chess.model.repository.connector;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessMySqlConnectorTest {

    @DisplayName("Connection Test")
    @Test
    public void connection() {
        Connection con = ChessMySqlConnector.getConnection();
        assertThat(Objects.nonNull(con)).isTrue();
    }
}