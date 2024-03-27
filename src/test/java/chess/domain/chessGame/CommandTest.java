package chess.domain.chessGame;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

public class CommandTest {

    private static Stream<Arguments> moveTestParameters() {
        return Stream.of(
                Arguments.of(List.of("move")),
                Arguments.of(List.of("move", "a2")),
                Arguments.of(List.of("move", "a2", "a3", "a4"))
        );
    }

    @DisplayName("적절하지 않은 커맨드의 경우 에외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"startt", " move ", "start move"})
    @EmptySource
    void commandTest(String invalidCommand) {
        Assertions.assertThatThrownBy(() -> Command.of(List.of(invalidCommand)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("%s는 올바른 커맨드가 아닙니다.", invalidCommand));
    }

    @DisplayName("커맨드가 move 일 때에 적절하지 않은 인자 수이면 예외를 발생한다.")
    @ParameterizedTest
    @MethodSource("moveTestParameters")
    void moveTest(List<String> invalidMoveCommands) {
        Assertions.assertThatThrownBy(() -> Command.of(invalidMoveCommands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("move 커맨드는 'move source위치 target위치' 3개의 인자로 이루어져야 합니다.");
    }
}
