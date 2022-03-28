package chess.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.game.Command;
import chess.game.MoveCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {

    @Test
    @DisplayName("게임이 종료된 상태에서 State가 변경될 수 없다.")
    void gameCannotRestart() {
        final State state = new Finished();

        assertThatThrownBy(() -> state.turn(Command.START))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되었습니다.");
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
                .hasMessage("게임이 종료되었습니다.");
    }

    @Test
    @DisplayName("게임이 종료된 상태에서 말을 움직일 수 있는지 확인한다.")
    void canMove() {
        final State state = new Finished();

        assertThat(state.canMove()).isFalse();
    }

    @Test
    @DisplayName("게임이 종료된 상태에서 체스판을 조회할 경우 예외를 발생한다.")
    void getBoards() {
        final State state = new Finished();

        assertThatThrownBy(state::getBoard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되었습니다.");
    }

    @Test
    @DisplayName("게임이 종료된 상태인지 확인한다.")
    void endGame() {
        final State state = new Finished();

        assertThat(state.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("게임이 종료된 상태에서 색깔을 조회할 경우 예외를 발생한다.")
    void color() {
        final State state = new Finished();

        assertThatThrownBy(state::getColor)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 종료되었습니다.");
    }

    @Test
    @DisplayName("게임이 종료된 상태에서 점수를 조회할 경우 빈 결과를 반환한다.")
    void score() {
        final State state = new Finished();

        assertThat(state.score()).isEmpty();
    }
}
