package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class CommandTest {

    @Test
    @DisplayName("move 명령어의 인자가 2개 미만일 경우 예외가 발생한다.")
    void validateMoveArgumentCount() {
        // given
        List<String> command = List.of("move", "b2");

        // when & then
        assertThatCode(() -> Command.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("move 명령어는 인자가 2개 필요합니다.");
    }
}
