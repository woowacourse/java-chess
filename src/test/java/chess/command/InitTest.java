package chess.command;

import chess.console.command.Command;
import chess.console.command.End;
import chess.console.command.Init;
import chess.console.command.Start;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InitTest {

    @Test
    @DisplayName("시작 명령어에 move가 들어오면 예외가 발생한다.")
    void turnStateFailureWhenMoveCommandTest() {
        String input = "move";
        Command command = new Init(input);
        assertThatThrownBy(() -> {
            command.turnState(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("명령어에 start 또는 end 이외의 명령어가 들어오면 예외가 발생한다.")
    void turnStateFailureWhenOtherCommandTest() {
        String input = "asd";
        assertThatThrownBy(() -> {
            new Init(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("start가 들어오면 Start로 상태가 변한다.")
    void turnStateStartTest() {
        String input = "start";
        Command command = new Init(input);
        assertThat(command.turnState(input)).isExactlyInstanceOf(Start.class);
    }

    @Test
    @DisplayName("end가 들어오면 End로 상태가 변한다.")
    void turnStateEndTest() {
        String input = "end";
        Command command = new Init(input);
        assertThat(command.turnState(input)).isExactlyInstanceOf(End.class);
    }

}
