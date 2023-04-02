package view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {
    InputView inputView;

    @Test
    @DisplayName("입력받은 값을 명령어 객체로 포장하여 가져올 수 있다.")
    void getCommand() {
        inputView = new InputView(new MockInputReader("start"));

        assertThat(inputView.getGameCommand())
                .isEqualTo(new Command("start"));
    }
}
