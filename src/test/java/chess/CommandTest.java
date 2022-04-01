package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CommandTest {

    @ParameterizedTest
    @CsvSource(value = {"start:START", "end:END", "status:STATUS", "move a1 a2:MOVE"}, delimiter = ':')
    @DisplayName("값을 통해 Command 를 잘 찾는지")
    void findCommand(String commandValue, Command expectedCommand) {
        Command command = Command.of(commandValue);

        assertThat(command).isEqualTo(expectedCommand);
    }

    @ParameterizedTest
    @ValueSource(strings = {"starrt", "End", "move", "move a2", "movement a1 a2"})
    @DisplayName("값이 잘못된 경우 예외를 발생시키는지")
    void findInvalidCommand(String commandValue) {
        assertThatThrownBy(() -> Command.of(commandValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 명령이 존재하지 않습니다.");
    }
}
