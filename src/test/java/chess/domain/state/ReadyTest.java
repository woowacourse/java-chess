package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ReadyTest {

    @DisplayName("start 이외의 명령을 실행하면 예외를 반환한다.")
    @ParameterizedTest
    @EnumSource(value = Command.class, names = {"MOVE", "END", "STATUS"})
    void cannot_Invalid_Command(Command command) {
        ChessState ready = new Ready();

        assertThatThrownBy(() -> ready.execute(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("start 명령을 실행하면 Running 상태를 반환한다.")
    @Test
    void start() {
        ChessState ready = new Ready();

        ChessState actual = ready.execute(Command.START);

        assertThat(actual).isInstanceOf(Running.class);
    }
}
