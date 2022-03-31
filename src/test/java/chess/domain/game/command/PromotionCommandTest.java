package chess.domain.game.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.game.state.MoveState;
import chess.domain.game.state.PromotionState;
import chess.domain.player.Players;

class PromotionCommandTest {

    @DisplayName("MoveState 상태에서만 사용 가능해야 한다.")
    @Test
    void notMoveStateException() {
        final MoveState moveState = new MoveState(new Players(Collections.emptyList()), Color.WHITE);
        assertThatThrownBy(() -> PromotionCommand.of(moveState, "Queen"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("프로모션이 가능한 게임 상태가 아닙니다.");
    }
}