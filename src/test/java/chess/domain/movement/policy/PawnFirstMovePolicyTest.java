package chess.domain.movement.policy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class FirstMovePolicyTest {

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("첫 이동 여부 정책과 일치 할 경우 해당 정책을 만족한다.")
    void isSatisfied(Color color) {
        FirstMovePolicy policy = new FirstMovePolicy();

        assertAll(
                () -> assertThat(policy.isSatisfied(color, true, false)).isTrue(),
                () -> assertThat(policy.isSatisfied(color, true, true)).isTrue()
        );
    }

    @ParameterizedTest
    @EnumSource(Color.class)
    @DisplayName("첫 이동 여부 정책과 일치 하지 않을 경우 해당 정책을 만족하지 않는다.")
    void isNotSatisfied(Color color) {
        FirstMovePolicy policy = new FirstMovePolicy();

        assertAll(
                () -> assertThat(policy.isSatisfied(color, false, false)).isFalse(),
                () -> assertThat(policy.isSatisfied(color, false, true)).isFalse()
        );
    }
}
