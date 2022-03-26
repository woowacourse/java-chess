package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadyTest {

    @DisplayName("Ready에서 start를 실행하면 WhiteTurn 상태로 변경된다.")
    @Test
    void Ready에서_start실행_WhiteTurn으로_상태가_변경된다() {
        State state = new Ready();

        state = state.start();

        assertThat(state).isInstanceOf(WhiteTurn.class);
    }

    @DisplayName("Ready에서 end를 실행하면 예외가 발생한다.")
    @Test
    void Ready에서_end실행_예외가_발생한다() {
        State state = new Ready();

        assertThatThrownBy(() -> state.end()).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Ready에서 move를 실행하면 예외가 발생한다.")
    @Test
    void Ready에서_move실행_예외가_발생한다() {
        State state = new Ready();

        assertThatThrownBy(() -> state.move("a1", "a2")).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Ready에서 isFinished를 호출하면 false가 반환된다.")
    @Test
    void Ready에서_isFinished호출_false가_반환된다() {
        State state = new Ready();

        assertThat(state.isFinished()).isFalse();
    }

    @DisplayName("Ready에서 winner를 실행하면 예외가 발생한다.")
    @Test
    void Ready에서_winner실행_예외가_발생한다() {
        State state = new Ready();

        assertThatThrownBy(() -> state.winner()).isInstanceOf(UnsupportedOperationException.class);
    }
}
