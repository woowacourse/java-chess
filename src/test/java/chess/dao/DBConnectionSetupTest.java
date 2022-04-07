package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBConnectionSetupTest {

    @DisplayName("DB Connection을 받아온다.")
    @Test
    void connect() {
        Connection connection = new DBConnectionSetup().getConnection();

        assertThat(connection).isNotNull();
    }
}
