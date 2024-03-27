package chess.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @DisplayName("주어진 값에 해당하는 커맨드를 찾는다.")
    @Test
    void findCommandByValue() {
        final String input = "start";

        final Command actual = Command.findByValue(input);

        assertThat(actual).isEqualTo(Command.START);
    }

    @DisplayName("주어진 값에 해당하는 커맨드가 없으면 예외가 발생한다.")
    @Test
    void occurExceptionWhenNotMatched() {
        final String input = "nyang";

        assertThatThrownBy(() -> Command.findByValue(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바르지 않은 명령어인 경우 예외가 발생한다.")
    @Test
    void occurExceptionWhenInvalidFormat() {
        final String input = "move b2b3";

        assertThatThrownBy(() -> Command.validateFormat(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
