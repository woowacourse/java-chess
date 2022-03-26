package chess.domain.game;

import chess.domain.board.BoardFixtures;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinishedTest {

    @Test
    @DisplayName("게임 시작시 에러가 발생한다.")
    void startTest() {
        GameState gameState = new Finished(BoardFixtures.initial(), Color.WHITE);

        assertThatThrownBy(gameState::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("게임 종료시 에러가 발생한다.")
    void finishTest() {
        GameState gameState = new Finished(BoardFixtures.initial(), Color.WHITE);

        assertThatThrownBy(gameState::finish)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("종료상태에서는 이동할 수 없다.")
    void throwsExceptionWithTryingToMove() {
        List<String> ignored = List.of("a1", "a2");
        GameState state = new Finished(BoardFixtures.initial(), Color.WHITE);

        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> state.move(ignored));
    }
}
