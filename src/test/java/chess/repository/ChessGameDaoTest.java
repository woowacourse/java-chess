package chess.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.infra.connection.JdbcTemplate;
import chess.infra.connection.TestConnectionPool;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
@TestMethodOrder(OrderAnnotation.class)
class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao(new JdbcTemplate(new TestConnectionPool()));

    @Test
    @Order(1)
    void save를_통해_새로운_보드를_생성할_수_있다() {
        //expect
        assertDoesNotThrow(() -> chessGameDao.save("test1"));
    }

    @Test
    @Order(2)
    void findBoardIdsByUserId() {
        //expect
        assertThat(chessGameDao.findBoardIdsByUserId("test1")).isNotEmpty();
    }

    @Test
    @Order(3)
    void delete() {
        //given
        int boardId = chessGameDao.findBoardIdsByUserId("test1").get(0);

        //expect
        assertDoesNotThrow(() -> chessGameDao.delete(boardId));
    }
}
