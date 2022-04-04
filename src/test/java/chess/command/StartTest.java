package chess.command;

import chess.console.command.Command;
import chess.console.command.End;
import chess.console.command.Move;
import chess.console.command.Start;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StartTest {

    @Test
    @DisplayName("end가 들어오면 End로 상태가 변한다.")
    void turnStateWhenEndTest() {
        Command start = new Start("start");
        Command command = start.turnState("end");
        assertThat(command).isExactlyInstanceOf(End.class);
    }

    @Test
    @DisplayName("move가 들어오면 Move로 상태가 변한다.")
    void turnStateWhenMoveTest() {
        String input = "move";
        Command start = new Start(input);
        Command command = start.turnState(input);
        assertThat(command).isExactlyInstanceOf(Move.class);
    }

    @Test
    @DisplayName("move또는 end이외의 명령어가 들어오면 예외가 발생한다.")
    void turnStateFailureWhenOtherCommandTest() {
        String input = "other";
        assertThatThrownBy(
                () -> new Start(input)
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("End인지 상태를 물어보면 항상 false를 반환한다.")
    void isEnd() {
        Command command = new Start("end");
        assertThat(command.isEnd()).isFalse();
    }
}
