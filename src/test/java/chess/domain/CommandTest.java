package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CommandTest {

    @DisplayName("등록된 명령이 아닌 경우 예외가 발생한다.")
    @Test
    void validateCommand() {

        assertThatThrownBy(() -> Command.valueOf("이상한 것"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 명령어가 존재한다.")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " [" + ARGUMENTS_PLACEHOLDER + "]")
    @CsvSource(value = {"start, START", "move, MOVE", "end, END"})
    void getCorrectCommand(String input, Command command) {

        assertThat(Command.from(input)).isEqualTo(command);
    }
}
