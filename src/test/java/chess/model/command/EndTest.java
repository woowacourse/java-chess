package chess.model.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class EndTest {
    @ParameterizedTest
    @ValueSource(strings = {"start", "end", "move", "status", " a"})
    @DisplayName("항상 end를 반환한다.")
    void turnState(String input) {
        Command command = new End("end");
        command = command.turnState(input);

        assertThat(command).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("end 클래스는 isEnd true를 반환한다")
    void isEnd() {
        Command command = new End("end");

        assertThat(command.isEnd()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "end", "move"})
    @DisplayName("start, end, move시에 end를 반환한다.")
    void turnFinalState(String input) {
        Command command = new End("end");
        command = command.turnFinalState(input);

        assertThat(command).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("status 입력시 status를 반환한다.")
    void turnFinalStateStatus() {
        Command command = new End("end");
        command = command.turnFinalState("status");

        assertThat(command).isInstanceOf(Status.class);
    }
}
