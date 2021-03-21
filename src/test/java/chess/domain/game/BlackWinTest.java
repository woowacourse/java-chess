package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.black.BlackQueen;
import chess.domain.piece.white.WhitePawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackWinTest {

    private BlackWin blackWin;

    @BeforeEach
    void setUp() {
        ChessGame chessGame = new ChessGame(new Board(Arrays.asList(
                BlackQueen.createWithCoordinate(0, 0),
                WhitePawn.createWithCoordinate(1, 1)
        )));
        blackWin = new BlackWin(chessGame);
    }

    @DisplayName("흑 승의 경우 흑 반환")
    @Test
    void getWinnerColor() {
        assertThat(blackWin.getWinnerColorNotation().isPresent()).isTrue();
        assertThat(blackWin.getWinnerColorNotation().get()).isEqualTo("흑");
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