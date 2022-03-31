package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputTest {

    @Test
    @DisplayName("명령어 입력 시 빈 값을 입력할 경우 예외 발생")
    void command_empty() {
        Enterable enterable = () -> "";

        assertThatThrownBy(() -> Input.inputCommand(enterable))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("값을 입력해주세요!");
    }

    @Test
    @DisplayName("명령어 입력 시 null 값을 입력할 경우 예외 발생")
    void command_null() {
        Enterable enterable = () -> null;

        assertThatThrownBy(() -> Input.inputCommand(enterable))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("값을 입력해주세요!");
    }
}