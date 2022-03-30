package chess.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndedTest {

    private State ended;

    @BeforeEach
    void setUp() {
        ended = new Ended();
    }

    @Test
    @DisplayName("ended state에서 start를 한 경우 ended가 나오는지 확인한다.")
    void start() {
        assertThat(ended.start()).isInstanceOf(Ended.class);
    }

    @Test
    @DisplayName("ended state에서 ended를 한 경우 ended가 나오는지 확인한다.")
    void end() {
        assertThat(ended.end()).isInstanceOf(Ended.class);
    }

    @Test
    @DisplayName("ended state에서 ended를 한 경우 ended가 나오는지 확인한다.")
    void move() {
        String[] commands = {"move", "b2", "b3"};
        assertThat(ended.move(commands)).isInstanceOf(Ended.class);
    }

    @Test
    @DisplayName("ended state에서 ended를 한 경우 ended가 나오는지 확인한다.")
    void status() {
        assertThat(ended.status()).isInstanceOf(Ended.class);
    }

}
