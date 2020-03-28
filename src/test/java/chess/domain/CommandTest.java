package chess.domain;

import chess.domain.command.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {


    @ParameterizedTest
    @ValueSource(strings = {"start", "end", "move", "status"})
    void Command_StringCommand_returnInstance(String command) {
        assertThat(Command.of(command)).isInstanceOf(Command.class);
    }

    @Test
    void Command_ErrorStringCommand_ExceptionThrown() {
        String command = "Asd";
        assertThatThrownBy(() -> Command.of(command)).isInstanceOf(IllegalArgumentException.class);
    }
}