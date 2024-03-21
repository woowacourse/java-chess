package chess.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameCommandTest {

    @Test
    @DisplayName("시작 문자열이 들어오면 시작 커멘드를 반환한다.")
    void mapStart() {
        String input = "start";

        GameCommand command = GameCommand.map(input);

        assertThat(command).isEqualTo(GameCommand.START);
    }

    @Test
    @DisplayName("종료 문자열이 들어오면 종료 커멘드를 반환한다.")
    void mapEnd() {
        String input = "end";

        GameCommand command = GameCommand.map(input);

        assertThat(command).isEqualTo(GameCommand.END);
    }

    @Test
    @DisplayName("허용하는 문자열이 아니면 예외가 발생한다.")
    void mapFail() {
        String input = "invalidCommand";

        assertThatThrownBy(() -> GameCommand.map(input))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] start, end, move만 입력할 수 있습니다.");
    }
}
