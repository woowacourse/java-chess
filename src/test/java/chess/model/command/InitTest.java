package chess.model.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InitTest {

    @Test
    @DisplayName("start를 입력하면 Start를 반환한다.")
    void inputStart() {
        Command command = new Init("start");
        command = command.turnState("start");

        assertThat(command).isInstanceOf(Start.class);
    }

    @Test
    @DisplayName("end를 입력하면 End를 반환한다.")
    void inputEnd() {
        Command command = new Init("end");
        command = command.turnState("end");

        assertThat(command).isInstanceOf(End.class);

    }

    @Test
    @DisplayName("move를 입력하면 예외처리를 한다.")
    void inputMove() {
        Command command = new Init("move a1 b2");

        assertThatThrownBy(() -> {
            command.turnState("move a1 b2");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("status를 입력하면 예외처리를 한다.")
    void inputStatus() {
        Command command = new Init("status");

        assertThatThrownBy(() -> {
            command.turnState("status");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
