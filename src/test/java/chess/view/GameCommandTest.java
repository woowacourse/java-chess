package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameCommandTest {

    @Test
    @DisplayName("시작 문자열이 들어오면 시작 커멘드를 반환한다.")
    void mapStart() {
        String input = "start";

        Command command = Command.of(input);

        assertThat(command).isEqualTo(Command.START);
    }

    @Test
    @DisplayName("종료 문자열이 들어오면 종료 커멘드를 반환한다.")
    void mapEnd() {
        String input = "end";

        Command command = Command.of(input);

        assertThat(command).isEqualTo(Command.END);
    }

    @Test
    @DisplayName("허용하는 문자열이 아니면 예외가 발생한다.")
    void mapFail() {
        String input = "invalidCommand";

        assertThatThrownBy(() -> Command.of(input))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("start, end, move만 입력할 수 있습니다.");
    }
}
