package chess.domain.gamestate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateTest {

    @DisplayName("Ready 상태에서 start 호출시 Running 상태가 된다.")
    @Test
    void ready_start_running() {
        State state = new Ready();

        assertThat(state.start()).isInstanceOf(Running.class);
    }

    @DisplayName("Running 상태에서 start 호출시 예외가 발생한다.")
    @Test
    void running_start_exception() {
        State state = new Running(new Board());

        assertThatThrownBy(state::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("진행 중일 때는 시작할 수 없습니다.");
    }

    @DisplayName("Finished 상태에서 start 호출시 Running 상태가 된다.")
    @Test
    void finished_start_running() {
        State state = new Finished(new Board());

        assertThat(state.start()).isInstanceOf(Running.class);
    }
}