package chess.game;

import chess.board.BoardFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RunningTest {

    @Test
    @DisplayName("게임 시작시 에러가 발생한다.")
    void startTest() {
        GameState gameState = new Running(BoardFixtures.INITIAL_BOARD);

        assertThatThrownBy(gameState::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("게임 종료시 종료 상태로 변한다.")
    void finishTest() {
        GameState gameState = new Running(BoardFixtures.INITIAL_BOARD);

        assertThat(gameState.finish()).isInstanceOf(Finished.class);
    }
}
