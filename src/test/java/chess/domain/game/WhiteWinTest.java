package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.game.state.finished.WhiteWin;
import chess.domain.piece.black.BlackPawn;
import chess.domain.piece.white.WhiteQueen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WhiteWinTest {

    private ChessGame chessGame;
    private WhiteWin whiteWin;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Board(Arrays.asList(
                WhiteQueen.createWithCoordinate(0, 0),
                BlackPawn.createWithCoordinate(0, 1)
        )));
        whiteWin = new WhiteWin(chessGame);
    }

    @Test
    void getWinnerColor() {
        assertThat(whiteWin.getWinnerColorNotation().isPresent()).isTrue();
        assertThat(whiteWin.getWinnerColorNotation().get()).isSameAs("백");
    }

    @Test
    void start() {
        assertThatThrownBy(() -> whiteWin.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void move() {
        assertThatThrownBy(() -> whiteWin.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void end() {
        assertThatThrownBy(() -> whiteWin.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isFinished() {
        boolean actual = whiteWin.isFinished();
        assertThat(actual).isTrue();
    }

}