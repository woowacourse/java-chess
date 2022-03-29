package chess.state;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadyTest {

    private State ready;

    @BeforeEach
    void setUp() {
        ready = new Ready();
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
        List<String> commands = List.of("move", "b2", "b3");
        assertThat(ready.move(commands)).isInstanceOf(Ready.class);
    }

    @Test
    void status() {
        assertThat(ready.status()).isInstanceOf(Ready.class);
    }

}
