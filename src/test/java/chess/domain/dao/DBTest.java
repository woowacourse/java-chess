package chess.domain.dao;

import chess.dao.ChessGameDao;
import chess.domain.board.strategy.InitialBoardStrategy;
import chess.domain.game.ChessGame;
import org.junit.jupiter.api.Test;

public class DBTest {

    @Test
    void save_테스트() {
        //given
        ChessGame chessGame = new ChessGame(new InitialBoardStrategy());
        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.save(chessGame);

        //when


        //then
    }
}
