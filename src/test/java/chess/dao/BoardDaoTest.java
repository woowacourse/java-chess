package chess.dao;

import chess.domain.Board;
import chess.view.BoardDto;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    @Test
    void save() {
        final BoardDao boardDao = new BoardDao();
        boardDao.saveBoard(BoardDto.of(Board.of()));
    }

    @Test
    void getBoard() {
        final BoardDao boardDao = new BoardDao();
        boardDao.getBoard();
    }

    @Test
    void remove() {
        final BoardDao boardDao = new BoardDao();
        boardDao.removeBoard();
    }
}
