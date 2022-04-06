package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.web.dto.BoardDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @DisplayName("보드 상태를 저장한다.")
    @Test
    void 보드_상태를_저장한다() {
        final BoardDaoForTest boardDao = new BoardDaoForTest();

        boardDao.save(new BoardDto("ready"));

        assertThat(boardDao.selectState()).isEqualTo("ready");
    }

    @DisplayName("보드 상태를 변경한다.")
    @Test
    void 보드_상태를_변경한다() {
        final BoardDaoForTest boardDao = new BoardDaoForTest();

        boardDao.save(new BoardDto("ready"));
        boardDao.update(new BoardDto("whiteTurn"));

        assertThat(boardDao.selectState()).isEqualTo("whiteTurn");
    }

    @DisplayName("보드 상태를 가져온다.")
    @Test
    void 보드_상태를_가져온다() {
        final BoardDaoForTest boardDao = new BoardDaoForTest();

        boardDao.save(new BoardDto("ready"));

        assertThat(boardDao.selectState()).isEqualTo("ready");
    }
}
