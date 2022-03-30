package chess.vo;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @DisplayName("명령어가 예외일 경우")
    @Test
    void command_exception() {
        assertThatThrownBy(() ->
            Command.startEnd("not found command")
        ).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(Command.NOT_FOUND_COMMAND_EXCEPTION);
    }

    @DisplayName("명령어가 정상 입력")
    @ParameterizedTest
    @ValueSource(strings = {"start", "end"})
    void command_normal(String input) {
        assertDoesNotThrow(() -> Command.startEnd(input));
    }

    @ParameterizedTest
    @CsvSource(value = {"start:START", "end:END"}, delimiter = ':')
    void startEnd_pass(String input, Command expected) {
        Command actual = Command.startEnd(input);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"starttt", "", " "})
    void startEnd_exception(String input) {
        assertThatThrownBy(() -> Command.startEnd(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Command.NOT_FOUND_COMMAND_EXCEPTION);
    }

    @ParameterizedTest
    @CsvSource(value = {"move a1 a2:MOVE", "end:END"}, delimiter = ':')
    void endMove_pass(String input, Command expected) {
        Command actual = Command.MoveStatusEnd(input);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"move a1 a9", "move a9 b1", "move B1 b2", "move a1 a2 a3"})
    void endMove_exception(String input) {
        assertThatThrownBy(() -> Command.MoveStatusEnd(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(Command.NOT_FOUND_COMMAND_EXCEPTION);
    }

    @ParameterizedTest
    @CsvSource(value = {"END:true", "STATUS:false", "START:false"}, delimiter = ':')
    @DisplayName("isEnd 메서드 검증")
    void isEnd(Command command, boolean expected) {
        assertThat(command.isEnd()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"END:false", "STATUS:true", "START:false"}, delimiter = ':')
    @DisplayName("isStatus 메서드 검증")
    void isStatus(Command command, boolean expected) {
        assertThat(command.isStatus()).isEqualTo(expected);
    }
}
