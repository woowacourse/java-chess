package chess.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackTurnTest {

    private ChessGame chessGame;
    private BlackTurn blackTurn;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(null);
        blackTurn = new BlackTurn(chessGame);
    }

    @Test
    void start() {
        assertThatThrownBy(() -> blackTurn.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isFinished() {
        boolean actual = blackTurn.isFinished();
        assertThat(actual).isFalse();
    }

}