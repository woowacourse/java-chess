package chess.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EndTest {

    private ChessGame chessGame;
    private End end;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(null);
        end = new End(chessGame);
    }

    @Test
    void move() {
        assertThatThrownBy(() -> end.move(null, null))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void start() {
        assertThatThrownBy(() -> end.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void end() {
        assertThatThrownBy(() -> end.end())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void isFinished() {
        boolean actual = end.isFinished();
        assertThat(actual).isTrue();
    }

}