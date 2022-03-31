package chess.domain.game.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.game.state.PromotionState;
import chess.domain.player.Players;

class MoveCommandTest {

    @DisplayName("MoveState 상태에서만 사용 가능해야 한다.")
    @Test
    void notMoveStateException() {
        final PromotionState promotionState = new PromotionState(new Players(Collections.emptyList()), Color.WHITE);
        assertThatThrownBy(() -> MoveCommand.of(promotionState, Position.from("a1"), Position.from("a2")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("기물을 움직일 수 있는 게임 상태가 아닙니다.");
    }
}