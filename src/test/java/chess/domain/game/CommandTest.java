package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.controller.Command;

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

}
