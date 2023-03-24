package chess.domain.database;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessGame;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @Test
    void connection() {
        assertThat(chessGameDao.getConnection()).isNotNull();
    }

    @Test
    void save() {
        ChessGame chessGame = new ChessGame();
        Assertions.assertThatCode(() -> chessGameDao.save(chessGame))
                .doesNotThrowAnyException();
    }

    @Test
    void select() {
        ChessGame chessGame = chessGameDao.select();
        assertThat(chessGame.getBoard()).hasSize(32);
    }
}
