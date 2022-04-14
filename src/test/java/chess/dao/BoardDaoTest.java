package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    @Test
    @DisplayName("체스 보드 상황을 저장한다.")
    void saveTest() {
        BoardDao boardDao = new BoardDao();
        ChessGameDao gameDao = new ChessGameDao();
        boardDao.save("a1", "Pawn", "WHITE", 1);
        boardDao.delete(1);
    }
}
