package chess.controller;

import static chess.controller.Command.END;
import static chess.controller.Command.MOVE;
import static chess.controller.Command.START;
import static chess.controller.Command.STATUS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @ParameterizedTest(name = "입력 값에 따라 command를 생성한다.")
    @MethodSource("commands")
    void createCommand(List<String> input, Command command) {
        assertThat(Command.from(input)).isEqualTo(command);
    }

    @ParameterizedTest(name = "입력 값이 start, end, move, status가 아니면 예외를 발생시킨다.")
    @ValueSource(strings = {"as", "strat", "mvoe"," "})
    void createCommand_fail(String input) {
        assertThatThrownBy(() -> Command.from(List.of(input)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("start, end, move, status 중에 입력해주세요.");
    }

    @ParameterizedTest(name = "start, end, status는 input size가 1, move는 3 이 아니면 예외가 발생한다.")
    @MethodSource("commandsForFail")
    void createCommand_fail_byInputSize(List<String> input) {
        assertThatThrownBy(() -> Command.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> commands() {
        return Stream.of(
                Arguments.arguments(List.of("start"), START),
                Arguments.arguments(List.of("end"), END),
                Arguments.arguments(List.of("status"), STATUS),
                Arguments.arguments(List.of("move", "a2", "a4"), MOVE)
        );
    }

    private static Stream<Arguments> commandsForFail() {
        return Stream.of(
                Arguments.arguments(List.of("start", "a1")),
                Arguments.arguments(List.of("end", "a2")),
                Arguments.arguments(List.of("status", "a3", "a4")),
                Arguments.arguments(List.of("move", "a4"))
        );
    }
}
