package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

class JdbcTemplateUtilTest {

    @Test
    void connection() {
        Connection connection = JdbcTemplateUtil.getConnection();
        assertThat(connection).isNotNull();
    }
}
