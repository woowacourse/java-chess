package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConnectionSetupTest {

    @DisplayName("DB Connection을 받아온다.")
    @Test
    void connect() {
        Connection connection = ConnectionSetup.getConnection();

        assertThat(connection).isNotNull();
    }
}
