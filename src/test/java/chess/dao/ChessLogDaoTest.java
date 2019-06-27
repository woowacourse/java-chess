package chess.dao;

import chess.application.dto.ChessLogDto;
import org.junit.jupiter.api.Test;

class ChessLogDaoTest {

    @Test
    void ChessDao_CRUD(){
        ChessLogDao chessLogDao = ChessLogDao.getInstance();





    }


    ChessLogDao chessLogDao = ChessLogDao.getInstance();

    @Test
    void insertChessInfoByRoundId() {
        ChessLogDto chessLogDto = new ChessLogDto("0", "5,6", "5,4");
        chessLogDao.insertChessLogByRoundId(2, chessLogDto);
    }

    @Test
    void selectChessInfoByRoundId() {
        chessLogDao.selectChessLogByRoundId(2);
    }

    @Test
    void selectTurnByRoundId() {
        System.out.println(chessLogDao.selectTurnByRoundId(2));
    }
}
