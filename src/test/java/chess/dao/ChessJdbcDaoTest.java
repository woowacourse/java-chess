package chess.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessJdbcDaoTest {
    private final ChessJdbcDao chessJdbcDao = new ChessJdbcDao();

    @Test
    @DisplayName("DB 연결")
    void connect() {
        try (final var connection = chessJdbcDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
