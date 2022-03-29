package chess.state;

import static org.assertj.core.api.Assertions.assertThat;

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
        String[] commands = {"move", "b2", "b3"};
        assertThat(ended.move(commands)).isInstanceOf(Ended.class);
    }

    @Test
    void status() {
        assertThat(ended.status()).isInstanceOf(Ended.class);
    }

}
