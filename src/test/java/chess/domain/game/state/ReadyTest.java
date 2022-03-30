package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.state.afterrunning.FinishedKing;
import chess.domain.game.state.beforerunning.Ready;
import chess.domain.game.state.running.RunningWhite;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @DisplayName("Ready에서 StartGame을 실행하면 RunningWhite가 반환된다")
    @Test
    void startGame_returnsRunningWhite() {
        // given
        State state = new Ready();

        // when
        State actual = state.startGame();

        // then
        assertThat(actual).isInstanceOf(RunningWhite.class);
    }

    @DisplayName("Ready에서 endGame을 실행하면 Ended가 반환된다")
    @Test
    void endGame_returnsEnded() {
        // given
        State state = new Ready();

        // when
        State actual = state.endGame();

        // then
        assertThat(actual).isInstanceOf(FinishedKing.class);
    }

    @DisplayName("Ready에서 move 메소드를 호출하면 예외가 발생한다.")
    @Test
    void move_throwsException() {
        // given
        State state = new Ready();

        // when & then
        assertThatThrownBy(() -> state.move(Position.from(XAxis.B, YAxis.TWO), Position.from(XAxis.A, YAxis.TWO)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임 시작 전 말을 움직일 수 없습니다.");
    }
}
