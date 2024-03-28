package chess.domain.db;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;

class ChessBoardDaoTest {

    private final ChessBoardDao chessBoardDao = new ChessBoardDao();

    @Test
    public void connection() {
        try (final var connection = chessBoardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
