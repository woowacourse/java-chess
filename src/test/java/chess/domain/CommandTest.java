package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CommandTest {
    @Test
    @DisplayName("start인지 확인한다.")
    void Command_First_command_is_only_start() {
        assertThatCode(() -> {
            Command.getStartCommand("start");
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("start가 아닌 입력을 하면 예외처리 된다.")
    void Command_Validate_first_command_is_start() {
        assertThatThrownBy(() -> {
            Command.getStartCommand("end");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임을 진행하는 명령어인지 검증한다.")
    void Command_Validate_command_while_playing() {
        assertThatCode(() -> {
            Command.getProcessCommand("end");
        }).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource(value = {"start", "abc"})
    @DisplayName("게임을 진행하는 명령어가 아닌지 검증한다.")
    void Command_Validate_wrong_command_while_playing(String command) {
        assertThatThrownBy(() -> {
            Command.getProcessCommand(command);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
