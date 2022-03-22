package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandTest {

    @Test
    @DisplayName("start Command가 존재하는지 확인한다.")
    void start() {
        assertThat(Command.of("start")).isEqualTo(Command.START);
    }

    @Test
    @DisplayName("end Command가 존재하는지 확인한다.")
    void end() {
        assertThat(Command.of("end")).isEqualTo(Command.END);
    }

    @Test
    @DisplayName("존재하지 않는 Command를 입력했을경우 예외를 발생시킨다.")
    void checkNonCommandException() {
        assertThatThrownBy(() -> Command.of("go"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 존재하지 않는 명령어입니다.");
    }
}
