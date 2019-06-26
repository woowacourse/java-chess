package chess.dao;

import chess.dto.ChessInfoDto;
import org.junit.jupiter.api.Test;

class ChessRoundDaoTest {
    private static final ChessRoundDao chessRoundDao = ChessRoundDao.getInstance();

    @Test
    void insertChessInfoByRoundId() {
        ChessInfoDto chessInfoDto = new ChessInfoDto("0", "5,6", "5,4");
        chessRoundDao.insertChessInfoByRoundId(2, chessInfoDto);
    }

    @Test
    void selectChessInfoByRoundId() {
        System.out.println(chessRoundDao.selectChessInfoByRoundId(2));
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