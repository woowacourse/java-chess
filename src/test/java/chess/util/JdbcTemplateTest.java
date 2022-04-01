package chess.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcTemplateTest {

    @Test
    @DisplayName("Connection 확인")
    void getConnection() {
        Connection connection = JdbcTemplate.getConnection();
        assertThat(connection).isNotNull();
    }

}