package chess.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WhiteTurnTest {

    private ChessGame chessGame;
    private WhiteTurn whiteTurn;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(null);
        whiteTurn = new WhiteTurn(chessGame);
    }

    @Test
    void start() {
        assertThatThrownBy(() -> whiteTurn.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isFinished() {
        boolean actual = whiteTurn.isFinished();
        assertThat(actual).isFalse();
    }

}
