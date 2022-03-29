package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.CachedPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @DisplayName("move 명령을 실행하면 예외를 반환한다.")
    @Test
    void cannot_Move() {
        ChessState ready = new Ready();

        assertThatThrownBy(() -> ready.move(CachedPosition.a1, CachedPosition.a2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("end 명령을 실행하면 예외를 반환한다.")
    @Test
    void cannot_End() {
        ChessState ready = new Ready();

        assertThatThrownBy(ready::end)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("start 명령을 실행하면 Running 상태를 반환한다.")
    @Test
    void start() {
        ChessState ready = new Ready();

        ChessState actual = ready.start();

        assertThat(actual).isInstanceOf(Running.class);
    }
}
