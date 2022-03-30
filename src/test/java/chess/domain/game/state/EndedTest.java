package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.game.state.Ended;
import chess.domain.game.state.State;
import chess.domain.position.Position;
import java.util.List;
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
        assertThat(ended.start()).isInstanceOf(Ended.class);
    }

    @Test
    void end() {
        assertThat(ended.end()).isInstanceOf(Ended.class);
    }

    @Test
    void move() {
        Position from = Position.create("b2");
        Position to = Position.create("b3");
        assertThat(ended.move(from, to)).isInstanceOf(Ended.class);
    }

    @Test
    void status() {
        assertThat(ended.status()).isInstanceOf(Ended.class);
    }

}
