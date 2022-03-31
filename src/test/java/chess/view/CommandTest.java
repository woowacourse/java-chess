package chess.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class CommandTest {

    @ParameterizedTest(name = "input : {0}, command : {1}")
    @MethodSource("provideForCommand")
    @DisplayName("해당하는 명령을 반환한다")
    void findCommand(final String input, final Command command) {
        assertThat(Command.of(input)).isEqualTo(command);
    }

    static Stream<Arguments> provideForCommand() {
        return Stream.of(
                Arguments.of("start", Command.START),
                Arguments.of("end", Command.END),
                Arguments.of("move", Command.MOVE)
        );
    }
}
