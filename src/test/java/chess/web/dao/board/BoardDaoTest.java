package chess.web.dao.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private BoardDao boardDao;

    @BeforeEach
    void setUp() {
        boardDao = new FakeBoardDao();
    }

    @DisplayName("board 테이블에 데이터를 생성한다.")
    @Test
    void name() {
        Assertions.assertDoesNotThrow(() -> boardDao.save(1, Map.of("a1", "w_r")));
    }

    @DisplayName("board 테이블에서 모든 데이터를 가져온다.")
    @Test
    void select_findAll() {
        boardDao.save(1, Map.of("a1", "w_r"));

        final Map<String, String> board = boardDao.findAll();

        assertThat(board).isNotNull();
    }

    @DisplayName("특정 roomId에 속한 board 데이터 전체를 업데이트한다.")
    @Test
    void update_board() {
        boardDao.save(1, Map.of("a1", "w_r"));

        boardDao.updateBoard(1, Map.of("a1", "w_p"));

        final Map<String, String> board = boardDao.findAll();
        assertThat(board.get("a1")).isEqualTo("w_p");
    }

    @AfterEach
    void tearDown() {
        boardDao.remove(1);
    }
}
