package chess.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandFactoryTest {

    @DisplayName("정의되지 않은 Command가 들어오면 예외를 반환한다.")
    @Test
    void invalid_Command() {
        assertThatThrownBy(() -> CommandFactory.find("invalid"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력에 맞는 명령을 찾을 수 없습니다.");
    }

    @DisplayName("move Command가 들어올 때 위치 인수가 없으면 예외를 반환한다.")
    @Test
    void invalid_Moving_Argument() {
        assertThatThrownBy(() -> CommandFactory.find("move"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 이동 명령입니다.");
    }
}
