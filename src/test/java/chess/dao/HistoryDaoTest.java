package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HistoryDaoTest {
    private final HistoryDao historyDao = new HistoryDao();

    @Test
    @DisplayName("DB 연결")
    void connect() {
        try (final var connection = historyDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
