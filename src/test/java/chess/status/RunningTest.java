package chess.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.game.Command;
import chess.game.MoveCommand;
import chess.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;

class RunningTest {

    @Test
    @DisplayName("게임 진행중에는 Start를 할 수 없다.")
    void gameCannotRestart() {
        final State state = new Running();

        assertThatThrownBy(() -> state.turn(Command.START))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 시작된 상태입니다.");
    }

    @Test
    @DisplayName("게임 진행 상태에서 게임을 끝내면 Finished 상태가 된다.")
    void finishFromRunning() {
        final State state = new Running();

        assertThat(state.turn(Command.END)).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("게임 진행 상태에서 말을 움직이면 Running 상태가 된다.")
    void continueRunning() {
        final State state = new Running();

        assertThat(state.turn(Command.MOVE)).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("Running 상태에서 게임이 진행 중인지 확인할 수 있다.")
    void isRunning() {
        final State state = new Running();

        assertThat(state.isRunning()).isTrue();
    }

    @Test
    @DisplayName("Running 상태에서 말을 움직일 수 있다.")
    void canMove() {
        final State running = new Running();

        assertThat(running.canMove()).isTrue();
    }

    @Test
    @DisplayName("한 번 움직이고 나면 화이트 턴에서 블랙 턴으로 변경된다.")
    void blackColor() {
        final State running = new Running();
        running.move(MoveCommand.of("b1", "a3"));

        assertThat(running.getColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("Black 차례에 White가 움직일 경우 예외를 발생한다.")
    void blackTurn() {
        final State running = new Running();
        running.move(MoveCommand.of("a2", "a4"));

        assertThatThrownBy(() -> running.move(MoveCommand.of("a4", "a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("BLACK가 둘 차례입니다.");
    }

    @Test
    @DisplayName("White 차례에 Black이 움직일 경우 예외를 발생한다.")
    void whiteTurn() {
        final State running = new Running();

        assertThatThrownBy(() -> running.move(MoveCommand.of("a7", "a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("WHITE가 둘 차례입니다.");
    }

    @Test
    @DisplayName("게임 진행중에 점수를 계산할 수 있다.")
    void score() {
        final State running = new Running();
        final Map<Color, Double> score = running.score();

        assertThat(score).containsValues(38.0, 38.0);
    }
}
