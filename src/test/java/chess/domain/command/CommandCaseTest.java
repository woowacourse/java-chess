package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class   CommandCaseTest {

    @Test
    @DisplayName("start 명령어를 받으면 START를 반환한다")
    void from_start() {
        final String command = "start";

        assertThat(CommandCase.from(command)).isEqualTo(CommandCase.START);
    }

    @Test
    @DisplayName("end 명령어를 받으면 END를 반환한다")
    void from_end() {
        final String command = "end";

        assertThat(CommandCase.from(command)).isEqualTo(CommandCase.END);
    }

    @Test
    @DisplayName("move 명령어를 받으면 MOVE를 반환한다")
    void from_move() {
        final String command = "move";

        assertThat(CommandCase.from(command)).isEqualTo(CommandCase.MOVE);
    }

    @Test
    @DisplayName("start, end, move가 아닌 다른 값을 받으면 예외가 발생한다")
    void invalidCommand() {
        final String command = "test";

        assertThatThrownBy(() -> CommandCase.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값은 start, end, move, status만 가능합니다.");
    }
}
