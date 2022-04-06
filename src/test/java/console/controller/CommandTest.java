package console.controller;

import chess.domain.board.Point;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.domain.game.Running;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class CommandTest {

    @Test
    @DisplayName("start를 넣으면 START를 반환한다.")
    void findStartCommand() {
        String input = "start";

        Command command = Command.find(input);

        assertThat(command).isEqualTo(Command.START);
    }

    @Test
    @DisplayName("end를 넣으면 END를 반환한다.")
    void findFinishCommand() {
        String input = "end";

        Command command = Command.find(input);

        assertThat(command).isEqualTo(Command.FINISH);
    }

    @Test
    @DisplayName("move를 넣으면 MOVE를 반환한다.")
    void findMoveCommand() {
        String input = "move";

        Command command = Command.find(input);

        assertThat(command).isEqualTo(Command.MOVE);
    }

    @Test
    @DisplayName("status를 넣으면 STATUS를 반환한다.")
    void findStatusCommand() {
        String input = "status";

        Command command = Command.find(input);

        assertThat(command).isEqualTo(Command.STATUS);
    }

    @Test
    @DisplayName("해당하는 명령이 없으면 예외가 발생한다.")
    void throwsExceptionMissingCommand() {
        String input = "finish";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Command.find(input));
    }

    @Test
    @DisplayName("execute 명령 실행시 변경된 상태를 반환한다.")
    void returnChangedState() {
        List<Point> ignored = new ArrayList<>();
        GameState state = new Ready();
        Command command = Command.START;

        GameState executed = command.execute(state, ignored);

        assertThat(executed).isInstanceOf(Running.class);
    }
}
