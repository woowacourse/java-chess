package chess.domain.gamestate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadyTest {

    @DisplayName("Ready 상태에서 start 호출시 Running 상태가 된다.")
    @Test
    void ready_start_running() {
        State state = new Ready();

        assertThat(state.start()).isInstanceOf(Running.class);
    }
}
