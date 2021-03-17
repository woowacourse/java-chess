package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @DisplayName("입력된 명령어에 따른 Command 타입을 반환한다.")
    @Test
    void startGame() {
        String value ="start";
        assertThat(Command.of(value)).isEqualTo(Command.START);
    }
}