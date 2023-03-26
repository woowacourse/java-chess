package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Turn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.OutputView;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @Test
    @DisplayName("보드를 저장하는 기능 테스트")
    void test_saveBoard() {
    }

    @Test
    @DisplayName("기물의 위치를 업데이트하는 기능 테스트")
    void test_updatePiecePosition() {

    }

    @Test
    @DisplayName("보드를 불러오는 기능 테스트")
    void test_loadBoard() {
        final BoardDao boardDao = new DataBaseBoardDao();
        final OutputView outputView = new OutputView();
        final Board board = boardDao.loadBoard(2L, new Turn());

        final Map<Position, Piece> boardMap = board.getBoard();
        outputView.printBoard(boardMap);
    }

    @Test
    @DisplayName("관련 테이블을 삭제하는 기능 테스트")
    void test_deleteBoard() {

    }
}
