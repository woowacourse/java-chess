package chess.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoveCommandTest {

    @ParameterizedTest(name = "moveCommand : {0}")
    @ValueSource(strings = {"a1 a2", "h7 h8", "d4 e5", "a6 a7", "g1 h2"})
    @DisplayName("String으로 입력받은 좌표로 이동 경로를 생성한다.")
    void createMoveCommand(final String actual) {
        assertThat(MoveCommand.of(actual)).isInstanceOf(MoveCommand.class);
    }

    @ParameterizedTest(name = "moveCommand : {0}")
    @ValueSource(strings = {"a1 a1", "a8, a8", "g1 g1", "g8 g8"})
    @DisplayName("현재 위치와 이동할 위치가 같은 경우 예외를 발생한다.")
    void validateFromEqualsTo(final String actual) {
        assertThatThrownBy(() -> MoveCommand.of(actual))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 위치와 이동할 위치가 같을 수 없습니다.");
    }
}

