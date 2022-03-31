package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinishedStateTest {

    @DisplayName("종료 상태임을 나타낼 수 있어야 한다.")
    @Test
    void isFinished() {
        final FinishedState gameState = new FinishedState();
        assertThat(gameState.isRunning()).isFalse();
    }
}