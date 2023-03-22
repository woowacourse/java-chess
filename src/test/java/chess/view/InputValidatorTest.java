package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    @Test
    @DisplayName("공백을 입력하면 예외가 발생한다.")
    void validateBlank() {
        List<String> commands = Collections.emptyList();
        assertThatThrownBy(() -> InputValidator.validateBlank(commands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 공백은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("입력이 start가 아니면 예외가 발생한다.")
    void validateStartCommand() {
        String input = "stort";
        assertThatThrownBy(() -> InputValidator.validateStartCommand(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 게임을 진행하기 위해서는 start를 입력해주세요.");
    }

    @Test
    @DisplayName("입력이 move가 아니면 예외가 발생한다.")
    void validateMoveCommand() {
        String input = "moove";
        assertThatThrownBy(() -> InputValidator.validateMoveCommand(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 게임을 진행하기 위해서는 move를 입력해주세요.");
    }

    @Test
    @DisplayName("move source위치 target위치 형식이 아니면 예외가 발생한다.")
    void validateCommandSize() {
        List<String> commands = List.of("move", "b1b2");
        assertThatThrownBy(() -> InputValidator.validateCommandSize(commands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] move source위치 target위치 형식으로 입력해주세요.");
    }

    @Test
    @DisplayName("source위치와 target위치의 길이가 2가 아니면 예외가 발생한다.")
    void validateCommandLength() {
        String sourceCommand = "b11";
        String targetCommand = "b";
        assertThatThrownBy(() -> InputValidator.validateCommandLength(sourceCommand, targetCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] source위치와 target위치는 두 글자로 입력해주세요.");
    }

    @Test
    @DisplayName("file의 범위가 a~h가 아니면 예외가 발생한다.")
    void validateFile() {
        char file = 'i';
        assertThatThrownBy(() -> InputValidator.validateFile(file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] file을 a~h로 입력해주세요.");
    }

    @Test
    @DisplayName("rank의 범위가 1~8이 아니면 예외가 발생한다.")
    void validateRank() {
        char rank = '9';
        assertThatThrownBy(() -> InputValidator.validateRank(rank))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] rank를 1~8로 입력해주세요.");
    }
}
