package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.state.Finished;
import chess.domain.state.Ready;
import chess.domain.state.Running;
import chess.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("최초 상태로 Ready를 생성할 수 있다.")
    void createStateReady() {
        assertThat(new Ready()).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("상태가 Ready일 때는 상태를 Running으로 변경할 수 있다.")
    void changeStateRunningToReady() {
        State state = new Ready();

        assertThat(state.start()).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("상태가 Ready일 때는 상태를 End로 변경할 수 있다.")
    void changeStateFinishedToReady() {
        State state = new Ready();

        assertThat(state.end()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("상태가 Ready일 때는 체스판을 보면 예외가 발생한다.")
    void invalidGetChessBoardToReady() {
        State state = new Ready();

        assertThatThrownBy(() -> state.getChessBoard())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("상태가 Ready인 경우 체스판을 호출 할 수 없습니다.");
    }

    @Test
    @DisplayName("상태가 Ready일 때는 점수를 확인하면 예외가 발생한다.")
    void invalidGetScoreToReady() {
        State state = new Ready();

        assertThatThrownBy(() -> state.getStatus())
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("상태가 Ready인 경우 상태를 호출 할 수 없습니다.");
    }

    @Test
    @DisplayName("상태가 Ready일 때는 게임의 종료 여부는 false이다.")
    void isFinishedToFalseToReady() {
        assertThat(new Ready().isFinished()).isFalse();
    }
}
