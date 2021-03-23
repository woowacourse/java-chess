package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.controller.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

    @DisplayName("Command 반환 : START")
    @Test
    void of_returnStart_success() {
        String value = "start";
        assertThat(Command.of(value)).isEqualTo(Command.START);
    }

    @DisplayName("Command 반환 : END")
    @Test
    void of_returnEnd_success() {
        String value = "end";
        assertThat(Command.of(value)).isEqualTo(Command.END);
    }

    @DisplayName("Command 반환 : MOVE")
    @Test
    void of_returnMove_success() {
        String value = "move";
        assertThat(Command.of(value)).isEqualTo(Command.MOVE);
    }

}