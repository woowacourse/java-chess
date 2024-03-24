package chess.domain;

import chess.view.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {
    @Test
    @DisplayName("start인지 확인한다.")
    void Command_First_command_is_only_start() {
        assertThatCode(() -> Command.getStartCommand("start"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("start가 아닌 입력을 하면 예외처리 된다.")
    void Command_Validate_first_command_is_start() {
        assertThatThrownBy(() -> Command.getStartCommand("end"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("첫 명령어는 start만 입력할 수 있습니다.");
    }

    @Test
    @DisplayName("게임을 진행하는 명령어인지 검증한다.")
    void Command_Validate_command_while_playing() {
        assertThatCode(() -> Command.getProcessCommand("end"))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource(value = {"start,게임 중에 start 명령어는 사용할 수 없습니다.", "abc,존재하지 않는 명령어입니다.",})
    @DisplayName("게임을 진행하는 명령어가 아닌지 검증한다.")
    void Command_Validate_wrong_command_while_playing(String command, String errorMessage) {
        assertThatThrownBy(() -> Command.getProcessCommand(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }
}
