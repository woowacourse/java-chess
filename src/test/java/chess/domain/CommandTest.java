package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandTest {

    @Test
    @DisplayName("start Command가 존재하는지 확인한다.")
    void start() {
        assertThat(Command.from("start")).isEqualTo(Command.START);
    }

    @Test
    @DisplayName("end Command가 존재하는지 확인한다.")
    void end() {
        assertThat(Command.from("end")).isEqualTo(Command.END);
    }

    @Test
    @DisplayName("move Command가 존재하는지 확인한다.")
    void move() {
        assertThat(Command.from("move")).isEqualTo(Command.MOVE);
    }

    @Test
    @DisplayName("status Command가 존재하는지 확인한다.")
    void status() {
        assertThat(Command.from("status")).isEqualTo(Command.STATUS);
    }

    @Test
    @DisplayName("존재하지 않는 Command를 입력했을경우 예외를 발생시킨다.")
    void checkNonCommandException() {
        assertThatThrownBy(() -> Command.from("go"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 명령어입니다.");
    }
}
