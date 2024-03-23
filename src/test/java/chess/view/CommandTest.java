package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    @DisplayName("사용자의 명령어 입력을 변환한다.")
    void from() {
        // given
        Command command = Command.from("start");

        // when & then
        assertThat(command).isEqualTo(Command.START);
    }
}