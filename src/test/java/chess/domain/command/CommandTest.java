package chess.domain.command;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CommandTest {

    @ParameterizedTest
    @MethodSource("provideCommandNameParameter")
    @DisplayName("값을 통해 Command 를 잘 찾는지")
    void findCommand(String commandValue, Command expectedCommand) {
        Command command = Command.of(commandValue);

        assertThat(command).isEqualTo(expectedCommand);
    }

    static Stream<Arguments> provideCommandNameParameter() {
        return Stream.of(
            Arguments.of("start", Command.START),
            Arguments.of("end", Command.END),
            Arguments.of("status", Command.STATUS),
            Arguments.of("move a1 a2", Command.MOVE)
        );
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
