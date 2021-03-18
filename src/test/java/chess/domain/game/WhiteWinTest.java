package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WhiteWinTest {

    private ChessGame chessGame;
    private WhiteWin whiteWin;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Board(PieceFactory.createPieces()));
        whiteWin = new WhiteWin(chessGame);
    }

    @Test
    void getWinnerColor() {
        assertThat(whiteWin.getWinnerColor().isPresent()).isTrue();
        assertThat(whiteWin.getWinnerColor().get()).isSameAs(Color.WHITE);
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