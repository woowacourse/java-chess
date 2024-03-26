package chess.domain.movement.policy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class EnemyExistPolicyTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("해당 위치의 적 유무 정책과 일치 할 경우 해당 정책을 만족한다.")
    void isSatisfied(Color color) {
        EnemyExistPolicy policy = new EnemyExistPolicy();

        assertThat(policy.isSatisfied(color, Position.of(1, 1), true)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("해당 위치의 적 유무 정책과 일치하지 않을 경우 해당 정책을 만족하지 않는다.")
    void isNotSatisfied(Color color) {
        EnemyExistPolicy policy = new EnemyExistPolicy();

        assertThat(policy.isSatisfied(color, Position.of(1, 1), false)).isFalse();
    }
}
