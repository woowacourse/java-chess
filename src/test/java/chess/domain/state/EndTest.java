package chess.domain.state;

import chess.domain.position.Position;
import chess.view.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class EndTest {

    @Test
    @DisplayName("start 시 예외 발생")
    void startException() {
        State end = new End();

        assertThatThrownBy(end::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료됐습니다.");
    }

    @Test
    @DisplayName("move 시 예외 발생")
    void moveException() {
        State end = new End();

        assertThatThrownBy(() -> end.move(Position.from("a2"), Position.from("a3")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료됐습니다.");
    }

    @Test
    @DisplayName("end 시 예외 발생")
    void endException() {
        State end = new End();

        assertThatThrownBy(end::end)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 이미 종료됐습니다.");
    }

    @Test
    @DisplayName("status 시 예외 발생")
    void statusException() {
        State end = new End();

        assertThatThrownBy(() -> end.status(Output::printScore))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료됐습니다.");
    }

    @Test
    @DisplayName("게임이 실행중이 아닌 것을 확인")
    void isRunning() {
        State end = new End();

        assertThat(end.isRunning()).isFalse();
    }

    @Test
    @DisplayName("게임이 끝난 것을 확인")
    void isEnded() {
        State end = new End();

        assertThat(end.isEnded()).isTrue();
    }
}