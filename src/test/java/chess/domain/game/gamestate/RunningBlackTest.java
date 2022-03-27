package chess.domain.game.gamestate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningBlackTest {
    @DisplayName("move를 호출하면 RunningWhite가 반환된다.")
    @Test
    void move_returnsRunningWhite() {
        // given
        State state = new RunningBlack(Board.createInitializedBoard());

        // when
        State actual = state.move(Position.from(XAxis.A, YAxis.SEVEN), Position.from(XAxis.A, YAxis.SIX));

        // then
        assertThat(actual).isInstanceOf(RunningWhite.class);
    }

    @DisplayName("startGame을 호출하면 예외가 발생한다.")
    @Test
    void startGame_throwsException() {
        // given
        State state = new RunningBlack(Board.createInitializedBoard());

        // when & then
        assertThatThrownBy(state::startGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임 중 입니다.");
    }

    @DisplayName("showStatus를 호출하면 FinishedStatus가 반환된다.")
    @Test
    void showStatus_returnsFinished() {
        // given
        State state = new RunningBlack(Board.createInitializedBoard());

        // when
        State actual = state.showStatus();

        // then
        assertThat(actual).isInstanceOf(FinishedStatus.class);
    }
}