package chess.domain.dao;

import chess.dao.DbChessGameDao;
import chess.domain.board.strategy.InitialBoardStrategy;
import chess.domain.game.ChessGame;
import org.junit.jupiter.api.Test;

public class DBTest {

    @Test
    void save_테스트() {
        //given
        ChessGame chessGame = new ChessGame(new InitialBoardStrategy());
        DbChessGameDao dbChessGameDao = new DbChessGameDao();
        dbChessGameDao.save(chessGame);

        //when


        //then
    }
}
