package chess.dao;

import org.junit.jupiter.api.Test;

class ChessRoundDaoTest {
    private static final ChessRoundDao chessRoundDao = ChessRoundDao.getInstance();

    @Test
    void selectLastRoundId() {
        System.out.println(chessRoundDao.selectLastRoundId());
    }

    @Test
    void insertRound() {
        chessRoundDao.insertRound(3);
    }
}