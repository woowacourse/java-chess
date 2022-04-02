package chess.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcTemplateTest {

    @Test
    @DisplayName("Connection 확인")
    void getConnection() throws SQLException {
        Connection connection = JdbcTemplate.getConnection();
        assertThat(connection).isNotNull();
        connection.close();
    }

    @Test
    @DisplayName("dev Connection 확인")
    void getConnectionTest() {
        Connection connection = JdbcTemplate.getConnection(JdbcTestFixture.DEV_URL);
        assertThat(connection).isNotNull();
    }

}