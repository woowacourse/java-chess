package chess.view.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoveArgumentsTest {

    @ParameterizedTest
    @MethodSource(value = "provideInvalidInputs")
    @DisplayName("move 명령어 인자의 형식이 올바르지 않으면 예외가 발생한다.")
    void validateArguments(List<String> inputs) {
        // when & then
        assertThatThrownBy(() -> MoveArguments.from(inputs))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<List<String>> provideInvalidInputs() {
        return Stream.of(
                List.of("move", "bb", "b1"),
                List.of("move", "bb", "bb"),
                List.of("move", "b1"),
                List.of("move")
        );
    }
}
