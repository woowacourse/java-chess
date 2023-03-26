package chess.database;

import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {
    @Test
    void addBoard() {
        ChessBoardDao chessBoardDao = new ChessBoardDao();
        chessBoardDao.addBoard(1, "b2", "p");
    }

}
