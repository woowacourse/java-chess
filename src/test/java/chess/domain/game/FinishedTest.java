package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.board.BoardFixtures;
import chess.dto.Arguments;

class FinishedTest {

    @Test
    @DisplayName("게임 시작시 에러가 발생한다.")
    void startTest() {
        GameState gameState = new Finished(BoardFixtures.INITIAL, Color.WHITE);

        assertThatThrownBy(gameState::start)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("게임 종료시 에러가 발생한다.")
    void finishTest() {
        GameState gameState = new Finished(BoardFixtures.INITIAL, Color.WHITE);

        assertThatThrownBy(gameState::finish)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("종료상태에서는 이동할 수 없다.")
    void throwsExceptionWithTryingToMove() {
        Arguments ignored = Arguments.ofArray(new String[] {"a1", "a2"}, 0);

        GameState state = new Finished(BoardFixtures.INITIAL, Color.WHITE);

        assertThatExceptionOfType(UnsupportedOperationException.class)
            .isThrownBy(() -> state.move(ignored));
    }

    @Test
    @DisplayName("종료상태는 진행 불가능한 상태이다.")
    void isRunnableIsFalse() {
        GameState state = new Finished(BoardFixtures.INITIAL, Color.WHITE);

        boolean isRunnable = state.isRunnable();

        assertThat(isRunnable).isFalse();
    }
}
