package chess.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    @DisplayName("잘못된 명령어으로 객체를 생성하려고 하면 예외를 발생시킨다.")
    void createException() {
        //given
        final String invalidValue = "delete";
        //when, then
        assertThatThrownBy(() -> Command.from(invalidValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 명령어입니다.");
    }
}
