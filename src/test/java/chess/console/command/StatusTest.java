package chess.console.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {

    @Test
    @DisplayName("status상태에서는 어떤 입력이 들어와도 끝나게 된다.")
    void turnState() {
        String input = "status";
        Command command = new Status(input);
        assertThat(command.turnState(input)).isExactlyInstanceOf(End.class);
    }
}
