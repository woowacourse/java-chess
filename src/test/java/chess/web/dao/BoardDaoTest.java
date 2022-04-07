package chess.web.dao;

import chess.board.Board;
import chess.board.Team;
import chess.board.Turn;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardDaoTest {

    private final BoardDao boardDao = new BoardDaoImpl();
    private Long boardId;

    @BeforeEach
    void setUp() {
        boardId = boardDao.save();
    }

    @AfterEach
    void tearDown(){
        boardDao.deleteById(boardId);
    }

    @Test
    @DisplayName("처음 저장된 board가 초기화된 Turn과 동일한지 테스트")
    void findTurnById() {
        //when
        Turn turn = boardDao.findTurnById(boardId).get();
        //then
        assertThat(turn).isEqualTo(Turn.init());
    }

    @Test
    @DisplayName("현재 턴을 black으로 업데이트하면 DB에 반영이 되는지 테스트")
    void updateTurnById() {
        //when
        boardDao.updateTurnById(boardId, "black");
        Turn turn = boardDao.findTurnById(boardId).get();
        //then
        assertThat(turn).isEqualTo(new Turn(Team.BLACK));
    }

    @Test
    @DisplayName("새로운 보드판이 만들어지면 pk값이 1 증가한다.")
    void save() {
        //when
        Long saveId = boardDao.save();
        //then
        assertThat(boardId + 1).isEqualTo(saveId);
        boardDao.deleteById(saveId);

    }

    @Test
    @DisplayName("현재 board판만 있고 piece들은 없으므로 size가 0이어야 한다.")
    void findById() {
        Board board = boardDao.findById(boardId).get();
        assertThat(board.getPieces().getPieces().size()).isEqualTo(0);
    }
}
