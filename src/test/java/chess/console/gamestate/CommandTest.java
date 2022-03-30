package chess.console.gamestate;

import static chess.console.gamestate.Command.MOVE;
import static chess.console.gamestate.Command.START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Position;
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

    @Test
    @DisplayName("MOVE 커맨드가 아닌데 moveposition계산 시 예외발생")
    void movePoisitonExceptionByNotCommand() {
        assertThatThrownBy(() -> START.movePosition("move a2 c5"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("position을 계산할 수 있는 Command가 아닙니다.");
    }

    @Test
    @DisplayName("MovePosition 계산")
    void movePosition() {
        MovePosition movePosition = MOVE.movePosition("move a2 c5");
        MovePosition expected = new MovePosition(Position.from("a2"), Position.from("c5"));

        assertThat(movePosition).isEqualTo(expected);
    }
}
