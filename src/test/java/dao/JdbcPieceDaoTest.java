package dao;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import util.DBUtil;

import java.sql.SQLException;

class JdbcPieceDaoTest {

    private final JdbcPieceDao jdbcChessBoardDao = new JdbcPieceDao();

    @Test
    public void connection() {
        try (final var connection = DBUtil.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
