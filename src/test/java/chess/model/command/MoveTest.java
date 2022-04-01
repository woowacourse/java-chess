package chess.model.command;

import chess.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoveTest {
    @Test
    @DisplayName("start를 입력하면 예외처리")
    void inputStart() {
        Command command = new Move("start");

        assertThatThrownBy(() -> {
            command.turnState("start");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("end를 입력하면 End를 반환한다.")
    void inputEnd() {
        Command command = new Move("end");
        command = command.turnState("end");

        assertThat(command).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("move를 입력하면 Move를 반환한다.")
    void inputMove() {
        Command command = new Move("move a1 b2");
        command = command.turnState("move a1 b2");

        assertThat(command).isInstanceOf(Move.class);
    }

    @Test
    @DisplayName("status를 입력하면 예외처리를 한다.")
    void inputStatus() {
        Command command = new Move("status");

        assertThatThrownBy(() -> {
            command.turnState("status");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "end", "move"})
    @DisplayName("start, end, move시에 end를 반환한다.")
    void turnFinalState(String input) {
        Command command = new Move("end");
        command = command.turnFinalState(input);

        assertThat(command).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("status 입력시 status를 반환한다.")
    void turnFinalStateStatus() {
        Command command = new Move("end");
        command = command.turnFinalState("status");

        assertThat(command).isInstanceOf(Status.class);
    }

    @Test
    @DisplayName("move 클래스는 isMove true를 반환한다")
    void isMove() {
        Command command = new Move("move");

        assertThat(command.isMove()).isTrue();
    }

    @Test
    @DisplayName("move 클래스는 isEnd false를 반환한다")
    void isEnd() {
        Command command = new Move("move");

        assertThat(command.isEnd()).isFalse();
    }

    @Test
    @DisplayName("source position을 찾는다")
    void getSourcePosition() {
        Command command = new Move("move a1 b2");

        assertThat(command.getSourcePosition()).isEqualTo(Position.from("a1"));
    }

    @Test
    @DisplayName("target position을 찾는다")
    void getTargetPosition() {
        Command command = new Move("move a1 b2");

        assertThat(command.getTargetPosition()).isEqualTo(Position.from("b2"));
    }
}
