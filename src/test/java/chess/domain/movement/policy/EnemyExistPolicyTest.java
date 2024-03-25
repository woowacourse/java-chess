package chess.domain.movement.policy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class EnemyExistPolicyTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("해당 위치의 적 유무 정책과 일치 할 경우 해당 정책을 만족한다.")
    void Given_EnemyExistPolicy_When_ExistEnemyIsTrue_Then_True(Color color) {
        //given
        EnemyExistPolicy policy = new EnemyExistPolicy();
        //when, then
        assertAll(
                () -> assertThat(policy.isSatisfied(color, true, true)).isTrue(),
                () -> assertThat(policy.isSatisfied(color, false, true)).isTrue()
        );
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("해당 위치의 적 유무 정책과 일치하지 않을 경우 해당 정책을 만족하지 않는다.")
    void Given_EnemyExistPolicy_When_ExistEnemyIsFalse_Then_False(Color color) {
        //given
        EnemyExistPolicy policy = new EnemyExistPolicy();
        //when, then
        assertAll(
                () -> assertThat(policy.isSatisfied(color, true, false)).isFalse(),
                () -> assertThat(policy.isSatisfied(color, false, false)).isFalse()
        );
    }
}
