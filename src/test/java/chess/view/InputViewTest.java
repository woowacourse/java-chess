package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.InputView;

class InputViewTest {
    @ParameterizedTest
    @ValueSource(strings = {"roy", "dino", "starto", " ", ""})
    @DisplayName("입력받은 값이 start가 아니면 예외처리한다.")
    void shouldFailInputIsNotStart(String input) {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> InputView.validateInitialCommand(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 게임을 시작하려면 start를 입력해 주세요");
    }
}
