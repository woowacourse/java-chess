package chess.view;

import static chess.view.InputView.NOT_SUPPORT_OPERATION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {

    @DisplayName("초기 시작시 올바른 명령어 입력")
    @ParameterizedTest
    @ValueSource(strings = {"start", "START", "end", "END"})
    void start_input_pass(String input) {
        assertDoesNotThrow(() -> InputView.validateStartCommand(input));
    }

    @DisplayName("초기 시작시 start 와 end 이외의 명령어를 입력한 경우")
    @Test
    void start_input_exception() {
        assertThatThrownBy(() -> InputView.validateStartCommand("move a2 a4"))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining(NOT_SUPPORT_OPERATION_MESSAGE);
    }

    @DisplayName("게임 시작시 명령어 정상 입력")
    @ParameterizedTest
    @ValueSource(strings = {"move a2 a4", "end", "status", "move B1 b2"})
    void in_gaming_input_pass(String input) {
        assertDoesNotThrow(()->InputView.validateCommandInGaming(input));
    }

    @DisplayName("정해진 형식의 move 명령어를 입력하지 않은 경우")
    @ParameterizedTest
    @ValueSource(strings = {"move a1 a9", "move a9 b1", "move a1 a2 a3"})
    void in_gaming_input_exception(String input) {
        assertThatThrownBy(() -> InputView.validateCommandInGaming(input))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining(NOT_SUPPORT_OPERATION_MESSAGE);
    }
}
