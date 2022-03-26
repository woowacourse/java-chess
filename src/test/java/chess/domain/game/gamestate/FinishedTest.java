package chess.domain.game.gamestate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedTest {

    @DisplayName("startGame을 호출하면 RunningWhite 상태가 된다")
    @Test
    void startReturnsRunningWhite() {
        // given
        State state = new FinishedKing(Board.createInitializedBoard());

        // when
        State actual = state.startGame();

        // then
        assertThat(actual).isInstanceOf(RunningWhite.class);
    }

    @DisplayName("endGame을 호출하면 예외가 발생한다.")
    @Test
    void endGame_throwsException() {
        // given
        State state = new FinishedKing(Board.createInitializedBoard());

        // when & then
        assertThatThrownBy(state::endGame)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 끝난 상태입니다.");
    }

    @DisplayName("move를 호출하면 예외가 발생한다.")
    @Test
    void move_throwsException() {
        // given
        State state = new FinishedKing(Board.createInitializedBoard());

        // when & then
        assertThatThrownBy(() -> state.move(Position.from(XAxis.B, YAxis.THREE), Position.from(XAxis.A, YAxis.FIVE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임 중이 아니어서 말을 움직일 수 없습니다.");
    }

    @DisplayName("showStatus를 호출하면 예외가 발생한다.")
    @Test
    void showStatus_throwsException() {
        // given
        State state = new FinishedKing(Board.createInitializedBoard());

        // when & then
        assertThatThrownBy(state::showStatus)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임이 끝난 상태입니다.");
    }
}