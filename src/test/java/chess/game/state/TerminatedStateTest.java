package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.board.Board;
import chess.board.BoardInitializer;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TerminatedStateTest {

    @Nested
    @DisplayName("게임이 종료됐을 때 ")
    class OnTerminatedState {

        @Test
        @DisplayName("진행 현황을 올바르게 반환한다.")
        void isPlayingTest() {
            // given
            TerminatedState terminatedState = new TerminatedState();
            // when
            boolean actual = terminatedState.isPlaying();
            // then
            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("모든 명령은 예외를 발생한다.")
        void startTest() {
            // given
            Board board = BoardInitializer.createBoard();
            Position source = Position.of(File.A, Rank.TWO);
            Position destination = Position.of(File.A, Rank.FOUR);
            TerminatedState terminatedState = new TerminatedState();
            // when, then
            assertAll(
                    () -> assertThatThrownBy(terminatedState::start)
                            .isInstanceOf(UnsupportedOperationException.class)
                            .hasMessage("게임이 이미 종료되었습니다."),
                    () -> assertThatThrownBy(() -> terminatedState.proceedTurn(board, source, destination))
                            .isInstanceOf(UnsupportedOperationException.class)
                            .hasMessage("게임이 이미 종료되었습니다."),
                    () -> assertThatThrownBy(terminatedState::terminate)
                            .isInstanceOf(UnsupportedOperationException.class)
                            .hasMessage("게임이 이미 종료되었습니다.")
            );
        }

        @Test
        @DisplayName("플레이 중을 검증한다.")
        void validatePlaying() {
            TerminatedState state = new TerminatedState();
            assertThatThrownBy(state::validatePlaying)
                    .isInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("게임이 진행되고 있지 않습니다.");
        }
    }

}
