package chess.dao;

import chess.domain.Board;
import chess.view.BoardDto;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    @Test
    void save() {
        final BoardDao boardDao = new BoardDao();
        boardDao.saveBoard(BoardDto.of(new Board()));
        boardDao.saveTurn("black");
    }

    @Test
    void getBoard() {
        final BoardDao boardDao = new BoardDao();
        boardDao.getBoard();
        System.out.println(boardDao.getBoard());
    }
}
