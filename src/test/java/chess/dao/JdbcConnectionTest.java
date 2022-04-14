package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JdbcConnectionTest {

    @Test
    @DisplayName("Connection 동작을 검증한다.")
    void connection() {

        //when
        Connection connection = JdbcConnection.getConnection();

        //then
        assertThat(connection).isNotNull();
    }
}
