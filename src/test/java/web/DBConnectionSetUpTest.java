package web;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import web.DBConnectionSetUp;

class DBConnectionSetUpTest {

    @Test
    @DisplayName("DB 커넥션이 정상적인지 확인")
    void connection() {
        Connection connection = DBConnectionSetUp.getConnection();
        assertThat(connection).isNotNull();
    }
}
