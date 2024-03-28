package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.board.Board;
import chess.board.BoardInitializer;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
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
        Board board = BoardInitializer.createBoard();
        Position source = Position.of(File.A, Rank.TWO);
        Position destination = Position.of(File.A, Rank.FOUR);
        InitState initState = new InitState();
        // when, then
        assertThatThrownBy(() -> initState.proceedTurn(board, source, destination))
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

    @Test
    @DisplayName("게임 진행 여부를 검증하면 예외를 발생한다.")
    void validatePlayingTest() {
        InitState initState = new InitState();
        assertThatThrownBy(initState::validatePlaying)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 진행되고 있지 않습니다.");
    }
}
