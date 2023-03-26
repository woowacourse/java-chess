package database.production;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectionFactoryTest {

    @Test
    @DisplayName("데이터베이스 커넥션이 정상적으로 생성된다.")
    void create_dbConnection() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();

        assertThat(connection).isNotNull();
        connection.close();
    }
}
