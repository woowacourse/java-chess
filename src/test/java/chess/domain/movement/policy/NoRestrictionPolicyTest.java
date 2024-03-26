package chess.domain.movement.policy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class NoRestrictionPolicyTest {
    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("조건과 상관 없이 참이 반환된다.")
    void isSatisfied(Color color) {
        NoRestrictionPolicy policy = new NoRestrictionPolicy();

        assertAll(
                () -> assertThat(policy.isSatisfied(color, Position.of(1, 1), false)).isTrue(),
                () -> assertThat(policy.isSatisfied(color, Position.of(1, 8), true)).isTrue(),
                () -> assertThat(policy.isSatisfied(color, Position.of(8, 1), false)).isTrue(),
                () -> assertThat(policy.isSatisfied(color, Position.of(8, 8), true)).isTrue()
        );
    }
}
