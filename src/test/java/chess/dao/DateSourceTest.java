package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.util.DataSource;
import chess.util.DataSourceImpl;
import java.sql.Connection;
import org.junit.jupiter.api.Test;

class DateSourceTest {

    @Test
    void connection() {
        DataSource dataSource = new DataSourceImpl();
        Connection connection = dataSource.getConnection();
        assertThat(connection).isNotNull();
    }
}
