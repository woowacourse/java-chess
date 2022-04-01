package chess.constant;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CommandTest {

    @DisplayName("명령어 정상 입력")
    @ParameterizedTest
    @CsvSource(value = {"start:START", "end:END"}, delimiter = ':')
    void startEnd_pass(String input, Command expected) {
        Command actual = Command.from(input);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("명령어 비정상 입력")
    @ParameterizedTest
    @ValueSource(strings = {"not found command", "", " "})
    void startEnd_exception(String input) {
        assertThatThrownBy(() -> Command.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Command.NOT_FOUND_COMMAND_EXCEPTION);
    }

    @DisplayName("명령어 정상 입력")
    @Test
    void endMove_pass() {
        assertThat(Command.from("move a1 a2")).isEqualTo(Command.MOVE);
    }
}
