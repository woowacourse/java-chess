package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitStateTest {

    @Test
    @DisplayName("게임이 시작되지 않았을 때 진행 현황을 올바르게 반환한다.")
    void isPlayingTest() {
        // given
        InitState initState = new InitState();
        // when
        boolean actual = initState.isPlaying();
        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("게임은 백의 선으로 시작된다.")
    void whiteTurnOnStartTest() {
        // given
        InitState initState = new InitState();
        // when
        GameState actual = initState.start();
        // then
        assertThat(actual).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("게임이 시작되지 않았을 때 움직이는 명령은 예외를 발생한다.")
    void moveBeforePlayingTest() {
        // given
        InitState initState = new InitState();
        // when, then
        assertThatThrownBy(() -> initState.proceedTurn((color) -> {}))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("처음 상태에서 종료 명령을 내리면 종료된다.")
    void terminateBeforePlayingTest() {
        // given
        InitState initState = new InitState();
        // when
        GameState actual = initState.terminate();
        // then
        assertThat(actual).isInstanceOf(TerminatedState.class);
    }
}
