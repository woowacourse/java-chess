package chess.domain.movement.policy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CombinationPolicyTest {

    @Test
    @DisplayName("조합한 정책을 전부 만족할 경우 참을 반환한다.")
    void Given_CombinationPolicy_When_IsSatisfiedAllPolicy_Then_True() {
        CombinationPolicy policy = new CombinationPolicy(new ColorPolicy(Color.WHITE), new FirstMovePolicy());

        assertAll(
                () -> assertThat(policy.isSatisfied(Color.WHITE, true, false)),
                () -> assertThat(policy.isSatisfied(Color.WHITE, true, true))
        );
    }

    @Test
    @DisplayName("조합한 정책 중 하나라도 만족하지 않을 경우 거짓을 반환한다.")
    void Given_CombinationPolicy_When_AnyPolicyNotSatisfied_Then_False() {
        CombinationPolicy policy = new CombinationPolicy(new ColorPolicy(Color.WHITE), new FirstMovePolicy());

        assertAll(
                () -> assertThat(policy.isSatisfied(Color.BLACK, true, false)),
                () -> assertThat(policy.isSatisfied(Color.WHITE, false, true))
        );
    }
}
