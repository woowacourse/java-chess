package chess.domain;

import static chess.constants.TestConstants.PARAMETERIZED_TEST_NAME;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CommandTypeTest {

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"start, START", "end, END", "MOVE, MOVE", "staTus, STATUS"})
    @DisplayName("문자열을 입력받아 커맨드를 얻는다.")
    void of(String commandName, CommandType expected) {
        assertThat(CommandType.of(commandName)).isEqualTo(expected);
    }

    @ParameterizedTest(name = PARAMETERIZED_TEST_NAME)
    @CsvSource(value = {"START, true", "END, true", "STATUS, true", "MOVE, false"})
    void isSingleCommand(CommandType commandType, boolean expected) {
        assertThat(CommandType.isSingleCommand(commandType)).isEqualTo(expected);
    }
}