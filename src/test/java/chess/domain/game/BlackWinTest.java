package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackWinTest {

    private ChessGame chessGame;
    private BlackWin blackWin;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Board(PieceFactory.createPieces()));
        blackWin = new BlackWin(chessGame);
    }

    @Test
    void getWinnerColor() {
        assertThat(blackWin.getWinnerColor().isPresent()).isTrue();
        assertThat(blackWin.getWinnerColor().get()).isSameAs(Color.BLACK);
    }

    @Test
    void start() {
        assertThatThrownBy(() -> blackWin.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void move() {
        assertThatThrownBy(() -> blackWin.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void end() {
        assertThatThrownBy(() -> blackWin.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isFinished() {
        boolean actual = blackWin.isFinished();
        assertThat(actual).isTrue();
    }

}