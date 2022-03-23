package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyStateTest {

    @Test
    @DisplayName("ready상태에서 start를 하면 게임이 실행된다.")
    void readyStartTest() {
        State state = new Ready();
        assertThat(state.start()).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("ready상태에서 end를 하면 예외가 발생한다..")
    void readyEndTest() {
        State state = new Ready();
        assertThatThrownBy(state::end).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("ready상태에서 getBoard를 실행하면 예외가 발생한다.")
    void readyGetBoardTest() {
        State state = new Ready();
        assertThatThrownBy(state::getBoard).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("ready상태에서 isRunning을 실행하면 false가 반환된다.")
    void readyIsRunningTest() {
        State state = new Ready();
        assertThat(state.isRunning()).isFalse();
    }

}