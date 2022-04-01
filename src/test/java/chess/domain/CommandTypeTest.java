package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CommandTypeTest {

    @ParameterizedTest
    @CsvSource(value = {"start, START", "end, END", "MOVE, MOVE", "staTus, STATUS"})
    @DisplayName("문자열을 입력받아 커맨드를 얻는다.")
    void of(final String commandName, final CommandType expected) {
        final CommandType type = CommandType.of(commandName);

        assertThat(type).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"START, true", "END, true", "STATUS, true", "MOVE, false"})
    void isSingleCommand(final CommandType commandType, final boolean expected) {
        final boolean isSingleCommand = CommandType.isSingleCommand(commandType);

        assertThat(isSingleCommand).isEqualTo(expected);
    }
}