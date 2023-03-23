package repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JdbcChessDaoTest {

    JdbcChessDao jdbcChessDao = new JdbcChessDao();

    @Test
    @DisplayName("DB 커넥션 테스트")
    void connection() throws SQLException {
        try(Connection connection = jdbcChessDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }


}
