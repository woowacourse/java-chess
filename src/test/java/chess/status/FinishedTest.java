package chess.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.MoveCommand;
import chess.view.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {

    @Test
    @DisplayName("게임이 종료된 상태에서 State가 변경될 수 없다.")
    void gameCannotRestart() {
        final State state = new Finished();

        assertThatThrownBy(() -> state.turn(Command.START))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 이미 종료되었습니다.");
    }

    @Test
    @DisplayName("Finished 상태에서 게임이 진행중인지 확인할 수 있다.")
    void finishFromRunning() {
        final State state = new Finished();

        assertThat(state.isRunning()).isFalse();
    }

    @Test
    @DisplayName("게임이 종료된 상태에서 말을 움직일 수 없다.")
    void continueRunning() {
        final State state = new Finished();

        assertThatThrownBy(() -> state.move(MoveCommand.of("a1 a2")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되어 말을 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("게임이 종료된 상태에서 말을 움직일 수 있는지 확인한다.")
    void canMove() {
        final State state = new Finished();

        assertThat(state.canMove()).isFalse();
    }

}
