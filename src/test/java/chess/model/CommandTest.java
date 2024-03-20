package chess.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @DisplayName("올바르지 않은 게임 명령어가 들어오면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", "starts", "END", "시작"})
    void findInvalidGameCommand(String command) {
        assertThatThrownBy(() -> Command.findCommand(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 게임 명령어입니다.");
    }

    @DisplayName("올바른 게임 명령어가 들어오면 해당 Command 상수를 반환한다")
    @Test
    void findValidGameCommand() {
        assertAll(
                () -> assertThat(Command.findCommand("start")).isEqualTo(Command.START),
                () -> assertThat(Command.findCommand("end")).isEqualTo(Command.END)
        );
    }
}
