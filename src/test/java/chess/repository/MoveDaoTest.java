package chess.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.infra.connection.JdbcTemplate;
import chess.infra.connection.TestConnectionPool;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
@TestMethodOrder(OrderAnnotation.class)
class MoveDaoTest {

    private final MoveDao moveDao = new MoveDao(new JdbcTemplate(new TestConnectionPool()));

    @Test
    @Order(1)
    void save를_통해_움직임을_저장할_수_있다() {
        assertDoesNotThrow(() -> moveDao.save(3, "a2", "a3", 1));
        assertDoesNotThrow(() -> moveDao.save(3, "a7", "a5", 2));
    }

    @Test
    @Order(2)
    void findMovesByBoardId() {
        assertThat(moveDao.findMovesByBoardId(3))
                .containsExactly(
                        List.of("a2", "a3", "1"),
                        List.of("a7", "a5", "2")
                );
    }

    @Test
    @Order(3)
    void deleteByBoardId() {
        assertDoesNotThrow(() -> moveDao.deleteByBoardId(3));
    }
}
