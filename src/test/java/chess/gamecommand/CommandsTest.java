package chess.gamecommand;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandsTest {

    @Test
    @DisplayName("move 명령어가 잘못된 포맷으로 들어오면 예외를 던진다.")
    void validateMoveCommand() {
        // given
        List<String> moveCommands = Arrays.asList("move", "a");

        // when, then
        assertThatThrownBy(() -> new Commands(moveCommands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] move 명령어는 소스 위치와 타겟 위치를 모두 입력해야 합니다.");
    }
}
