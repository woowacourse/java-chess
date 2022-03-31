package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    void start() {
        End end = new End(Board.create());
        Assertions.assertThatThrownBy(() -> end.start())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void end() {
        End end = new End(Board.create());
        Assertions.assertThatThrownBy(() -> end.end())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void move() {
        End end = new End(Board.create());
        Assertions.assertThatThrownBy(() -> end.move(Coordinate.of("a2"), Coordinate.of("a4")))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void isFinished() {
        End end = new End(Board.create());
        assertThat(end.isFinished()).isTrue();
    }
}
