package chess.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @Test
    @DisplayName("Command에 올바른 문자열 입력 시 정상적으로 생성")
    void create() {
        assertAll(() -> {
            assertThat(Command.from("start")).isInstanceOf(StartCommand.class);
            assertThat(Command.from("end")).isInstanceOf(EndCommand.class);
            assertThat(Command.from("move b2 b4")).isInstanceOf(MoveCommand.class);
            assertThat(Command.from("status")).isInstanceOf(StatusCommand.class);
        });
    }
}
