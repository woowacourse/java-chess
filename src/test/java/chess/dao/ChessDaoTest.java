package chess.dao;

import chess.domain.board.Board;
import chess.domain.state.Black;
import chess.domain.state.White;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ChessDaoTest {

    @Test
    void saveState() {
        ChessDao chessDao = new ChessDao();
        chessDao.saveState(new Black(new Board()));
        String state = chessDao.getState();
        Assertions.assertThat(state).isEqualTo("black");
    }

    @Test
    void update() {
        ChessDao chessDao = new ChessDao();
        chessDao.saveState(new Black(new Board()));
        chessDao.updateState(new White(new Board()));
        String state = chessDao.getState();
        Assertions.assertThat(state).isEqualTo("white");
    }

    @Test
    void deleteAll() {
        ChessDao chessDao = new ChessDao();
        chessDao.deleteAll();
        Assertions.assertThat(chessDao.getState()).isNull();
    }
}
