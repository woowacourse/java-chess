package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.coordinate.Coordinate;
import org.junit.jupiter.api.Test;

public class StartTest {
    @Test
    void start() {
        State start = new Start();
        State whiteTurn = start.start();
        assertThat(whiteTurn).isInstanceOf(WhiteTurn.class);
    }

    @Test
    void end() {
        State start = new Start();
        assertThatThrownBy(() -> start.end())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void move() {
        State start = new Start();
        assertThatThrownBy(() -> start.move(Coordinate.of("a2"), Coordinate.of("a4")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void is_finished() {
        Start start = new Start();
        assertThat(start.isFinished()).isFalse();
    }
}
