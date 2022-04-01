package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    @DisplayName("Command에 올바른 문자열 입력 시 정상적으로 실행되는지 확인")
    void create() {
        assertAll(() -> {
            assertThat(Command.from("start").isStart()).isTrue();
            assertThat(Command.from("end").isEnd()).isTrue();
            assertThat(Command.from("move b2 b4").isMove()).isTrue();
            assertThat(Command.from("status").isStatus()).isTrue();
        });
    }
}
