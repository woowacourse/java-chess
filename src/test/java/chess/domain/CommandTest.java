package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandTest {

    @DisplayName("등록된 명령이 아닌 경우 예외가 발생한다.")
    @Test
    void validateCommand() {

        assertThatThrownBy(() -> new Command("이상한 것"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 명령이 아닙니다.");
    }

    @DisplayName("입력된 명령이 시작 명령인지 확인한다.")
    @Test
    void verifyCommand_IsStart() {
        Command command = new Command("start");

        assertThat(command.isStart()).isTrue();
    }

    @DisplayName("입력된 명령이 종료 명령인지 확인한다.")
    @Test
    void verifyCommand_IsEnd() {
        Command command = new Command("end");

        assertThat(command.isEnd()).isTrue();
    }
}
