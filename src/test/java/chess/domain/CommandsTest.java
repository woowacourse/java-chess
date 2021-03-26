package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.command.Commands;
import chess.domain.command.EndCommand;
import chess.domain.command.MoveCommand;
import chess.domain.command.StartCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandsTest {

    @DisplayName("Command 반환 : START")
    @Test
    void of_returnStart_success() {
        String value = "start";
        assertThat(Commands.of(value, new Game())).isInstanceOf(StartCommand.class);
    }

    @DisplayName("Command 반환 : END")
    @Test
    void of_returnEnd_success() {
        String value = "end";
        assertThat(Commands.of(value, new Game())).isInstanceOf(EndCommand.class);
    }

    @DisplayName("Command 반환 : MOVE")
    @Test
    void of_returnMove_success() {
        String value = "move a2 a8";
        assertThat(Commands.of(value, new Game())).isInstanceOf(MoveCommand.class);
    }

}