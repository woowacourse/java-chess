package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadyTest {

    private State ready;

    @BeforeEach
    void setUp() {
        ready = new Ready(new Board(BoardInitializer.create()));
    }

    @Test
    void start() {
        assertThat(ready.start()).isInstanceOf(Started.class);
    }

    @Test
    void end() {
        assertThat(ready.end()).isInstanceOf(Ended.class);
    }

    @Test
    void move() {
        Position from = Position.create("b2");
        Position to = Position.create("b3");
        assertThatThrownBy(() -> ready.move(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] start를 하지 않아 move 할 수 없습니다.");
    }

    @Test
    void status() {
        assertThatThrownBy(() -> ready.status())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] start를 하지 않아 status 할 수 없습니다.");
    }
}
