package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.state.beforerunning.FinishedKing;
import chess.domain.game.state.beforerunning.FinishedStatus;
import chess.domain.game.state.running.RunningBlack;
import chess.domain.game.state.running.RunningWhite;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningWhiteTest {
    @DisplayName("move를 호출하면 RunningBlack이 반환된다.")
    @Test
    void move_returnsRunningBlack() {
        // given
        State state = new RunningWhite(BoardFactory.createInitializedBoard());

        // when
        State actual = state.move(Position.from(XAxis.A, YAxis.TWO), Position.from(XAxis.A, YAxis.THREE));

        // then
        assertThat(actual).isInstanceOf(RunningBlack.class);
    }

    @DisplayName("startGame을 호출하면 예외가 발생한다.")
    @Test
    void startGame_throwsException() {
        // given
        State state = new RunningWhite(BoardFactory.createInitializedBoard());

        // when & then
        assertThatThrownBy(state::startGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임 중 입니다.");
    }

    @DisplayName("move를 호출하고 상대편 킹을 잡았을 경우 Finished가 반환된다.")
    @Test
    void move_ifKillKingReturnsFinished() {
        // given
        State state = new RunningWhite(BoardFactory.createInitializedBoard());

        // when
        state.move(Position.from(XAxis.B, YAxis.ONE), Position.from(XAxis.C, YAxis.THREE));
        state.move(Position.from(XAxis.C, YAxis.THREE), Position.from(XAxis.B, YAxis.FIVE));
        state.move(Position.from(XAxis.B, YAxis.FIVE), Position.from(XAxis.C, YAxis.SEVEN));
        State actual = state.move(Position.from(XAxis.C, YAxis.SEVEN), Position.from(XAxis.E, YAxis.EIGHT));

        // then
        assertThat(actual).isInstanceOf(FinishedKing.class);
    }

    @DisplayName("showStatus를 호출하면 FinishedStatus가 반환된다.")
    @Test
    void showStatus_returnsFinished() {
        // given
        State state = new RunningWhite(BoardFactory.createInitializedBoard());

        // when
        State actual = state.showStatus();

        // then
        assertThat(actual).isInstanceOf(FinishedStatus.class);
    }
}
