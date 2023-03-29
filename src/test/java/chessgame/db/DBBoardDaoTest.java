package chessgame.db;

import org.junit.jupiter.api.Test;

class DBBoardDaoTest {
    private final DBBoardDao DBBoardDao = new DBBoardDao();

    @Test
    public void addBoard() {
        DBBoardDao.addBoard();
    }
}
