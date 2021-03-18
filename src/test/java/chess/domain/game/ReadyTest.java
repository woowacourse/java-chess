package chess.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    private ChessGame chessGame;
    private Ready ready;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(null);
        ready = new Ready(chessGame);
    }

    @Test
    void move() {
        assertThatThrownBy(() -> ready.move(null, null))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void end() {
        assertThatThrownBy(() -> ready.end())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isFinished() {
        boolean actual = ready.isFinished();
        assertThat(actual).isFalse();
    }

}