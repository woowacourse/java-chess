package chess.gamestate;

import static chess.gamestate.Command.MOVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @Test
    @DisplayName("커맨드 생성 시 null 입력 예외발생")
    void toCommandNullException() {
        assertThatThrownBy(() -> Command.toCommand(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("command는 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"st", "move aa z1", "END"})
    @DisplayName("없는 명령 입력 시 예외발생")
    void toCommandNotFoundException(String input) {
        assertThatThrownBy(() -> Command.toCommand(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 명령어가 아닙니다.");
    }

    @Test
    @DisplayName("command를 반환")
    void toCommand() {
        Command command = Command.toCommand("move a2 c5");
        assertThat(command).isEqualTo(MOVE);
    }
}
