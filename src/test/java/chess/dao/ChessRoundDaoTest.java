package chess.dao;

import chess.application.dto.ChessLogDto;
import org.junit.jupiter.api.Test;

class ChessRoundDaoTest {
    private static final ChessRoundDao chessRoundDao = ChessRoundDao.getInstance();

    @Test
    void insertChessInfoByRoundId() {
        ChessLogDto chessLogDto = new ChessLogDto("0", "5,6", "5,4");
        chessRoundDao.insertChessLogByRoundId(2, chessLogDto);
    }

    @Test
    void selectChessInfoByRoundId() {
        System.out.println(chessRoundDao.selectChessLogByRoundId(2));
    }

    @Test
    void selectTurnByRoundId() {
        System.out.println(chessRoundDao.selectTurnByRoundId(2));
    }

    @Test
    void selectLastRoundId() {
        System.out.println(chessRoundDao.selectLastRoundId());
    }

    @Test
    void insertRound() {
        chessRoundDao.insertRound(3);
    }
}