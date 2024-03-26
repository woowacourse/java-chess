package chess.domain.movement.policy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CombinationPolicyTest {

    @Test
    @DisplayName("조합한 정책을 전부 만족할 경우 참을 반환한다.")
    void isSatisfied() {
        CombinationPolicy policy = new CombinationPolicy(new ColorPolicy(Color.WHITE), new PawnFirstMovePolicy());

        assertAll(
                () -> assertThat(policy.isSatisfied(Color.WHITE, Position.of(1, 2), false)),
                () -> assertThat(policy.isSatisfied(Color.WHITE, Position.of(1, 2), true))
        );
    }

    @Test
    @DisplayName("조합한 정책 중 하나라도 만족하지 않을 경우 거짓을 반환한다.")
    void isNotSatisfied() {
        CombinationPolicy policy = new CombinationPolicy(new ColorPolicy(Color.WHITE), new PawnFirstMovePolicy());

        assertAll(
                () -> assertThat(policy.isSatisfied(Color.BLACK, Position.of(1, 2), false)),
                () -> assertThat(policy.isSatisfied(Color.WHITE, Position.of(1, 7), true))
        );
    }

}
