package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @Test
    @DisplayName("보드를 저장하는 기능 테스트")
    void test_saveBoard() {
        final Board board = new BoardFactory().createInitialBoard();
        BoardDao boardDao = new DataBaseBoardDao();
        boardDao.saveBoard(board, 2);
    }

    @Test
    @DisplayName("기물의 위치를 업데이트하는 기능 테스트")
    void test_updatePiecePosition() {

    }

    @Test
    @DisplayName("보드를 불러오는 기능 테스트")
    void test_loadBoard() {

    }

    @Test
    @DisplayName("관련 테이블을 삭제하는 기능 테스트")
    void test_deleteBoard() {

    }
}
