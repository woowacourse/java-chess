package chess.view.consoleview.input;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTypeTest {

    @Test
    @DisplayName("잘못된 명령어를 입력하면 예외를 발생시킨다.")
    void createException() {
        //given
        final String invalidCommand = "jump";
        //when, then
        assertThatThrownBy(() -> CommandType.from(invalidCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("명령어 입력이 잘못되었습니다.");
    }

}