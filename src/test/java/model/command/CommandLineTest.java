package model.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidCommandException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CommandLineTest {

    @DisplayName("유효하지 않은 커맨드가 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidInputParameterProvider")
    void invalidInput(List<String> input) {
        assertThatThrownBy(() -> CommandLine.from(input))
                .isInstanceOf(InvalidCommandException.class);
    }

    static Stream<Arguments> invalidInputParameterProvider() {
        return Stream.of(
                Arguments.of(List.of("start", "a2", "a3")),
                Arguments.of(List.of("start", "end")),
                Arguments.of(List.of("move", "a2")),
                Arguments.of(List.of("move", "a2", "a3", "a4")),
                Arguments.of(List.of("end", "a2", "a3")),
                Arguments.of(List.of("end", "end"))
        );
    }
}
