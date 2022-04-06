package chess.web.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.web.dto.BoardDto;
import chess.web.dto.PieceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @DisplayName("보드 상태를 저장한다.")
    @Test
    void 보드_상태를_저장한다() {
        final BoardDaoForTest boardDao = new BoardDaoForTest();

        boardDao.save(new BoardDto("ready"));

        assertThat(boardDao.getBoardState()).isEqualTo("ready");
    }

    @DisplayName("보드 상태를 변경한다.")
    @Test
    void 보드_상태를_변경한다() {
        final BoardDaoForTest boardDao = new BoardDaoForTest();

        boardDao.save(new BoardDto("ready"));
        boardDao.update(new BoardDto("whiteTurn"));

        assertThat(boardDao.getBoardState()).isEqualTo("whiteTurn");
    }
}
