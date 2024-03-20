package chess.view;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStartCommandTest {

    @Test
    @DisplayName("시작 문자열이 들어오면 시작 커멘드를 반환한다.")
    void mapStart() {
        String input = "start";

        GameStartCommand command = GameStartCommand.map(input);

        assertThat(command).isEqualTo(GameStartCommand.START);
    }

    @Test
    @DisplayName("종료 문자열이 들어오면 종료 커멘드를 반환한다.")
    void mapEnd() {
        String input = "end";

        GameStartCommand command = GameStartCommand.map(input);

        assertThat(command).isEqualTo(GameStartCommand.END);
    }

    @Test
    @DisplayName("허용하는 문자열이 아니면 예외가 발생한다.")
    void mapFail() {
        String input = "invalidCommand";

        assertThatThrownBy(() -> GameStartCommand.map(input))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] start 또는 end만 입력할 수 있습니다.");
    }
}