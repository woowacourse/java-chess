package chess.model.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusTest {
    @ParameterizedTest
    @ValueSource(strings = {"start", "end", "move", "status"})
    @DisplayName("항상 end를 반환한다.")
    void turnState(String input) {
        Command command = new Status("status");
        command = command.turnState(input);

        assertThat(command).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("isStatus true를 반환한다.")
    void isStatus() {
        Command command = new Status("status");

        assertThat(command.isStatus()).isTrue();
    }
}
