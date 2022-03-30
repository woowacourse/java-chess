package chess.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    private State ready;

    @BeforeEach
    void setUp() {
        ready = new Ready();
    }

    @Test
    @DisplayName("ready state에서 start를 한 경우 started 나오는지 확인한다.")
    void start() {
        assertThat(ready.start()).isInstanceOf(Started.class);
    }

    @Test
    @DisplayName("ready state에서 start를 한 경우 ended가 나오는지 확인한다.")
    void end() {
        assertThat(ready.end()).isInstanceOf(Ended.class);
    }

    @Test
    @DisplayName("ready state에서 move를 한 경우 예외가 발생하는지 확인한다.")
    void move() {
        String[] commands = {"move", "b2", "b3"};
        Assertions.assertThatThrownBy(() -> ready.move(commands))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] start를 하지 않아 move 할 수 없습니다.");
    }

    @Test
    @DisplayName("ready state에서 status를 한 경우 예외가 발생하는지 확인한다.")
    void status() {
        Assertions.assertThatThrownBy(() -> ready.status())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] start를 하지 않아 status 할 수 없습니다.");
    }

}
