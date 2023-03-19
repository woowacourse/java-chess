package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.commnad.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @ParameterizedTest(name = "invalid command : {0}")
    @EmptySource
    @ValueSource(strings = {"1", "2", "3", "a2 b2", "moving"})
    @DisplayName("잘못된 게임 명령어 입력이 들어오면 예외를 발생시킨다.")
    void throws_exception_when_input_invalid_command(final String input) {
        // when & then
        assertThatThrownBy(() -> Command.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
