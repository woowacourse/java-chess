package chess.model.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StartTest {
    @Test
    @DisplayName("start를 입력하면 예외처리")
    void inputStart() {
        Command command = new Start("start");

        assertThatThrownBy(() -> {
            command.turnState("start");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("end를 입력하면 End를 반환한다.")
    void inputEnd() {
        Command command = new Start("start");
        command = command.turnState("end");

        assertThat(command).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("move를 입력하면 Move를 반환한다.")
    void inputMove() {
        Command command = new Start("start");
        command = command.turnState("move a1 b2");

        assertThat(command).isInstanceOf(Move.class);
    }

    @Test
    @DisplayName("status를 입력하면 예외처리를 한다.")
    void inputStatus() {
        Command command = new Start("start");

        assertThatThrownBy(() -> {
            command.turnState("status");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
