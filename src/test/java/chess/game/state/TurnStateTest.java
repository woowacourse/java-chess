package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.board.Board;
import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TurnStateTest {

    TurnState createDummyTurnState() {
        return new TurnState() {
            @Override
            public GameState proceedTurn(Board board, Position source, Position destination) {
                return null;
            }
        };
    }

    @Nested
    @DisplayName("게임이 진행 중일 때 ")
    class OnPlaying {

        @Test
        @DisplayName("진행 현황을 올바르게 반환한다.")
        void isPlayingTest() {
            // given
            TurnState state = createDummyTurnState();
            // when
            boolean actual = state.isPlaying();
            // then
            assertThat(actual).isTrue();
        }

        @Test
        @DisplayName("시작 명령은 예외를 발생한다.")
        void startTest() {
            // given
            TurnState state = createDummyTurnState();
            // when, then
            assertThatThrownBy(state::start)
                    .isInstanceOf(UnsupportedOperationException.class)
                    .hasMessage("게임이 이미 시작되었습니다.");
        }

        @Test
        @DisplayName("종료 명령은 종료 상태로 전이한다.")
        void endTest() {
            // given
            TurnState state = createDummyTurnState();
            // when
            GameState actual = state.terminate();
            // then
            assertThat(actual).isInstanceOf(TerminatedState.class);
        }

        @Test
        @DisplayName("플레이 여부를 검증한다.")
        void validatePlaying() {
            assertDoesNotThrow(() -> createDummyTurnState().validatePlaying());
        }
    }
}
