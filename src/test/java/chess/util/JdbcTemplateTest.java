package chess.util;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.DataSourceImpl;
import chess.dao.TestDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcTemplateTest {

    @Test
    @DisplayName("Connection 확인")
    void getConnection() throws SQLException {
        Connection connection = new DataSourceImpl().getConnection();
        assertThat(connection).isNotNull();
        connection.close();
    }

    @Test
    @DisplayName("dev Connection 확인")
    void getConnectionTest() {
        Connection connection = new TestDataSource().getConnection();
        assertThat(connection).isNotNull();
    }

}
