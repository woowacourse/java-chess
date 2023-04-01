package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DbChessGameDaoTest {
    private final DbChessGameDao dbChessGameDAO = new DbChessGameDao();

    @Test
    @DisplayName("DB가 정상적으로 연결된다.")
    public void connection() {
        try (final var connection = dbChessGameDAO.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


