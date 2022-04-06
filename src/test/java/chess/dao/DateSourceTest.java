package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.web.dao.DataSource;
import java.sql.Connection;
import org.junit.jupiter.api.Test;

class DateSourceTest {

    @Test
    void connection() {
        DataSource dataSource = new DataSource();
        Connection connection = dataSource.getConnection();
        assertThat(connection).isNotNull();
    }
}
