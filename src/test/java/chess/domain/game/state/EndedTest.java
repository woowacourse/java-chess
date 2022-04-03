package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EndedTest {

    private State ended;

    @BeforeEach
    void setUp() {
        ended = new Ended();
    }

    @Test
    void start() {
        assertThatThrownBy(() -> ended.start())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임이 끝나 start 할 수 없습니다.");
    }

    @Test
    void end() {
        assertThatThrownBy(() -> ended.end())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임이 끝나 end 할 수 없습니다.");
    }

    @Test
    void move() {
        Position from = Position.create("b2");
        Position to = Position.create("b3");
        assertThatThrownBy(() -> ended.move(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임이 끝나 move 할 수 없습니다.");
    }

    @Test
    void status() {
        assertThatThrownBy(() -> ended.status())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임이 끝나 status 할 수 없습니다.");
    }

}
