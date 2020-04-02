package chess.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTypeTest {

    @Test
    void findValueOf_inputCommandInstance_returnCommandType() {
        Command command = Command.from("move");

        assertThat(CommandType.findValueOf(command)).isEqualTo(CommandType.MOVE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "end"})
    void checkInitialCommand_CommandTypeIsStartOrEnd_ReturnBoolean(String input) {
        Command command = Command.from(input);

        assertThat(CommandType.findValueOf(command).checkInitialCommand()).isTrue();
    }

    @Test
    void isEnd_InputCommandEnd_returnTrue() {
        Command command = Command.from("end");

        assertThat(CommandType.findValueOf(command).isEnd()).isTrue();
    }

    @Test
    void isStart_InputCommandEnd_returnTrue() {
        Command command = Command.from("start");

        assertThat(CommandType.findValueOf(command).isStart()).isTrue();
    }
}