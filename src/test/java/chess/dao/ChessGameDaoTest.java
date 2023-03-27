package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.chessGame.ChessGame;
import chess.domain.dao.ChessGameDao;
import org.junit.jupiter.api.Test;

public class ChessGameDaoTest {

    @Test
    void 저장_테스트() {
        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.delete();
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        assertDoesNotThrow(() -> chessGameDao.save(chessGame));
        chessGameDao.delete();
    }

    @Test
    void 조회_테스트() {
        ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.delete();
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move("a2", "a4");
        chessGameDao.save(chessGame);
        chessGame = chessGameDao.select();

        assertThat(chessGame.getTurnName()).isEqualTo("BLACK");
    }
}
