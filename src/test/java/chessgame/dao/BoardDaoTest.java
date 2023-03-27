package chessgame.dao;

import org.junit.jupiter.api.Test;

class BoardDaoTest {
    private final BoardDao boardDao = new BoardDao();

    @Test
    public void addBoard() {
        boardDao.addBoard();
    }
}
