package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

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
        Command actual = Command.endMove(input);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"move a1 a9", "move a9 b1", "move B1 b2", "move a1 a2 a3"})
    void endMove_exception(String input) {
        assertThatThrownBy(() -> Command.endMove(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Command.NOT_FOUND_COMMAND_EXCEPTION);
    }
}
