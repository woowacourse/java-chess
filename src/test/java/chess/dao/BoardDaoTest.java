package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.fixture.MockBoardDao;
import chess.piece.detail.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @Test
    @DisplayName("보드에 현재 상태를 저장한다.")
    void saveBoardTurn() {
        BoardDao boardDao = new MockBoardDao();
        boardDao.save(Color.WHITE);
    }

    @Test
    @DisplayName("id로 현재 턴을 확인한다.")
    void findTurnById() {
        final BoardDao boardDao = new MockBoardDao();
        boardDao.save(Color.WHITE);
        final Color turn = boardDao.findTurnById(1);
        assertThat(turn).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("보드를 로드한다.")
    void loadMockBoard() {
        final BoardDao boardDao = new MockBoardDao();
        boardDao.save(Color.WHITE);
        final Color turn = boardDao.load();
        assertThat(turn).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("마지막으로 사용된 보드를 찾는다.")
    void findLastlyUsedMockBoard() {
        final BoardDao boardDao = new MockBoardDao();
        boardDao.save(Color.WHITE);
        assertThat(boardDao.findLastlyUsedBoard()).isEqualTo(1);
    }

    @Test
    @DisplayName("보드를 업데이트 한다.")
    void updateMockBoardById() {
        final BoardDao boardDao = new MockBoardDao();
        boardDao.save(Color.WHITE);
        final int boardId = boardDao.findLastlyUsedBoard();
        boardDao.updateById(boardId, Color.BLACK);

        assertThat(boardDao.findTurnById(boardId)).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("보드 아이디를 통해 보드를 삭제한다.")
    void deleteMockBoardById() {
        final MockBoardDao boardDao = new MockBoardDao();
        boardDao.save(Color.WHITE);
        final int boardId = boardDao.findLastlyUsedBoard();
        boardDao.deleteById(boardId);
        assertThat(boardDao.getBoard().size()).isEqualTo(0);
    }
}
