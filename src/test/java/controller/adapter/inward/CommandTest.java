package controller.adapter.inward;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CommandTest {

    @Test
    @DisplayName("허용되지 않은 커맨드 값이 들어오면 예외를 발생시킨다")
    void throwExceptionWhenAbuse() {
        assertThatThrownBy(() -> Command.of(List.of("movb")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("허용된 커맨드 값이 들어오면 정상 흐름이다")
    void notThrowExceptionWhenAbuse() {
        assertThatCode(() -> Command.of(List.of("start")))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("커맨드가 START이면 참이다")
    void isStart() {
        Command command = Command.START;

        assertThat(command).isEqualTo(Command.START);
    }

    @Test
    @DisplayName("커맨드가 END이면 참이다")
    void isNotEnd() {
        Command command = Command.END;

        assertThat(command).isEqualTo(Command.END);
    }

    @Test
    @DisplayName("커맨드가 MOVE이면 참이다")
    void isMove() {
        Command command = Command.MOVE;

        assertThat(command).isEqualTo(Command.MOVE);
    }
}
