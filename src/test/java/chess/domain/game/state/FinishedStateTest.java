package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.player.PlayerFactory;
import chess.domain.player.Players;

class FinishedStateTest {

    @DisplayName("종료 상태임을 나타낼 수 있어야 한다.")
    @Test
    void isFinished() {
        final FinishedState gameState = new FinishedState(Players.initialize(PlayerFactory.getInstance()), Color.WHITE);
        assertThat(gameState.isRunning()).isFalse();
    }
}