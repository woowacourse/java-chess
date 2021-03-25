package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    private static Stream<Arguments> inputAndExpectedCommand() {
        return Stream.of(
            Arguments.of("start", Command.START),
            Arguments.of("end", Command.END),
            Arguments.of("move b99 c33", Command.MOVE),
            Arguments.of("status", Command.STATUS)
        );
    }

    @ParameterizedTest
    @DisplayName("입력에 맞는 커맨드 반환")
    @MethodSource("inputAndExpectedCommand")
    void commandByInput(String input, Command expectedCommand) {
        assertThat(Command.commandByInput(input)).isEqualTo(expectedCommand);
    }

    @ParameterizedTest
    @DisplayName("입력에 맞는 커맨드 반환")
    @ValueSource(strings = {"start a1", "end b2", "status f1", "move a1", "move a1 b2 c3"})
    void invalidCommandByInput(String input) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> Command.commandByInput(input))
            .withMessage("유효하지 않은 입력입니다.");
    }

    @Test
    @DisplayName("인자 리스트를 구하는 테스트")
    void arguments() {
        assertThat(Command.arguments("start")).isEqualTo(Collections.emptyList());
    }
}