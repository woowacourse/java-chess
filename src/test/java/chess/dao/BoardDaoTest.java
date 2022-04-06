package chess.dao;

import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    @Test
    void save() {
        final BoardDao boardDao = new BoardDao();
        ChessGameDao chessGameDao = new ChessGameDao();
        boardDao.save("a1", "pawn", "white", chessGameDao.findRecentGame());
    }
}
