//package chess.dao;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import chess.dao.fixture.MockBoardDao;
//import chess.piece.detail.Color;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class BoardDaoTest {
//
//    @Test
//    @DisplayName("보드에 현재 상태를 저장한다.")
//    void saveBoardTurn() {
//        BoardDao boardDao = new MockBoardDao();
//        boardDao.save(Color.WHITE);
//    }
//
//    @Test
//    @DisplayName("id로 현재 턴을 확인한다.")
//    void findTurnById() {
//        final BoardDao boardDao = new MockBoardDao();
//
//        final int boardId = boardDao.save(Color.WHITE);
//        final Color turn = boardDao.findTurnById(boardId);
//        assertThat(turn).isEqualTo(Color.WHITE);
//    }
//}
