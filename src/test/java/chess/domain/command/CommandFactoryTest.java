package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandFactoryTest {
    @DisplayName("초기화시 start가 들어오면 start 상태를 반환한다. ")
    @Test
    void startCommandTest() {
        Command command = CommandFactory.initialCommand("start");
        assertThat(command).isInstanceOf(Start.class);
    }

    @DisplayName("초기화시 end가 들어오면 end 상태를 반환한다. ")
    @Test
    void endCommandTest() {
        Command command = CommandFactory.initialCommand("end");
        assertThat(command).isInstanceOf(End.class);
    }

    @DisplayName("초기화시 잘못된 커맨드가 들어오면 예외가 발생한다. ")
    @Test
    void commandExceptionTest() {
        assertThatThrownBy(() -> CommandFactory.initialCommand("eee"))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}