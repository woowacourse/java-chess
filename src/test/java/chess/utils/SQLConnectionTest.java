package chess.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SQLConnectionTest {

    @Test
    @DisplayName("connection을 확인한다.")
    void connection() throws SQLException {
        final Connection connection = SQLConnection.getConnection();
        assertThat(connection).isNotNull();
        connection.close();
    }
}
