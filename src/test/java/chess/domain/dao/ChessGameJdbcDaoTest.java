package chess.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.ChessGameJdbcDao;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ChessGameJdbcDaoTest {

    @Test
    void DB를_연결한다() {
        try (final var connection = new ChessGameJdbcDao().getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
