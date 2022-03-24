package chess.domain.game;

import chess.domain.board.BoardFixtures;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinishedTest {

    @Test
    @DisplayName("게임 시작시 에러가 발생한다.")
    void startTest() {
        GameState gameState = new Finished(BoardFixtures.INITIAL_BOARD, Color.WHITE);

        assertThatThrownBy(gameState::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("게임 종료시 에러가 발생한다.")
    void finishTest() {
        GameState gameState = new Finished(BoardFixtures.INITIAL_BOARD, Color.WHITE);

        assertThatThrownBy(gameState::finish)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("[ERROR]");
    }
}
