package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.state.StateType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardStateDaoTest {

    @DisplayName("보드 상태를 저장한다.")
    @Test
    void 보드_상태를_저장한다() {
        final MockBoardStateDao boardStateDao = new MockBoardStateDao();

        boardStateDao.save(StateType.READY);

        assertThat(boardStateDao.selectState()).isEqualTo(StateType.READY);
    }

    @DisplayName("보드 상태를 변경한다.")
    @Test
    void 보드_상태를_변경한다() {
        final MockBoardStateDao boardStateDao = new MockBoardStateDao();

        boardStateDao.save(StateType.READY);
        boardStateDao.update(StateType.WHITE_TURN);

        assertThat(boardStateDao.selectState()).isEqualTo(StateType.WHITE_TURN);
    }

    @DisplayName("보드 상태를 가져온다.")
    @Test
    void 보드_상태를_가져온다() {
        final MockBoardStateDao boardStateDao = new MockBoardStateDao();

        boardStateDao.save(StateType.READY);

        assertThat(boardStateDao.selectState()).isEqualTo(StateType.READY);
    }

    @DisplayName("보드 상태를 전부 삭제한다.")
    @Test
    void 보드_상태를_전부_삭제한다() {
        final MockBoardStateDao boardStateDao = new MockBoardStateDao();

        boardStateDao.save(StateType.READY);
        boardStateDao.deleteAll();

        assertThat(boardStateDao.selectState()).isNull();
    }
}
