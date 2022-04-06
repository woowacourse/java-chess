package chess.dao;

import chess.domain.board.Board;
import chess.domain.state.Black;
import chess.domain.state.State;
import java.sql.Connection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ChessDaoTest {

    @Test
    void connection() {
        ChessDao chessDao = new ChessDao();
        Connection connection = chessDao.getConnection();
        Assertions.assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        ChessDao chessDao = new ChessDao();
        chessDao.saveState(new Black(new Board()));
        State state = chessDao.getState();
        Assertions.assertThat(state).isInstanceOf(Black.class);
    }

    @Test
    void deleteAll() {
        ChessDao chessDao = new ChessDao();
        chessDao.deleteAll();
    }
}
