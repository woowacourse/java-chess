package chess.command;

import chess.console.command.Command;
import chess.console.command.End;
import chess.console.command.Move;
import chess.console.command.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MoveTest {

    @Test
    @DisplayName("명령어가 들어오면 잘 분리하는지 테스트")
    void getCommandPositionTest() {
        Command move = new Move("move a1 a2");
        List<String> commandPosition = move.getCommandPosition();
        assertThat(commandPosition.get(0)).isEqualTo("a1");
        assertThat(commandPosition.get(1)).isEqualTo("a2");
    }

    @Test
    @DisplayName("move 명령어가 들어오면 Move상태로 변한다.")
    void turnStateWhenMoveTest() {
        String input = "move";
        Command move = new Move(input);
        Command command = move.turnState(input);
        assertThat(command).isExactlyInstanceOf(Move.class);
    }

    @Test
    @DisplayName("end 명령어가 들어오면 End상태로 변한다..")
    void turnStateWhenEndTest() {
        String input = "end";
        Command move = new Move(input);
        Command command = move.turnState(input);
        assertThat(command).isExactlyInstanceOf(End.class);
    }

    @Test
    @DisplayName("마지막 명령어로 status가 들어오면 Status상태로 변한다.")
    void turnFinalState() {
        String input = "move";
        Command move = new Move(input);
        String status = "status";
        Command command = move.turnFinalState(status);
        assertThat(command).isExactlyInstanceOf(Status.class);
    }
}
