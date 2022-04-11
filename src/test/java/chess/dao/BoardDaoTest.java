package chess.dao;

import org.junit.jupiter.api.Test;

public class BoardDaoTest {

    @Test
    void save() {
        final BoardDao boardDao = new BoardDao();
        ChessGameDao chessGameDao = new ChessGameDao();
        int gameId = chessGameDao.findRecentGame();
    }

    @Test
    void update() {
        final BoardDao boardDao = new BoardDao();
        ChessGameDao chessGameDao = new ChessGameDao();
        int gameId = chessGameDao.findRecentGame();
    }
}
