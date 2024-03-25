package chess.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("명령어 종류")
class CommandTypeTest {

    @Test
    @DisplayName("사용자의 입력을 변환한다.")
    void fromTest() {
        // given
        CommandType commandType = CommandType.from("start");

        // when & then
        assertThat(commandType).isEqualTo(CommandType.START);
    }
}
