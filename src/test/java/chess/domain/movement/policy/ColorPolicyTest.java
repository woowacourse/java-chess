package chess.domain.movement.policy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ColorPolicyTest {

    @ParameterizedTest
    @CsvSource({"false,false", "false,true", "true,false", "true,true"})
    @DisplayName("색깔 정책과 일치하는 색일 경우 해당 정책을 만족한다.")
    void Given_ColorPolicy_When_IsSatisfiedWithSameColor_Then_True(boolean firstMove, boolean existEnemy) {
        //given
        ColorPolicy policy = new ColorPolicy(Color.WHITE);
        //when, then
        assertThat(policy.isSatisfied(Color.WHITE, firstMove, existEnemy)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"false,false", "false,true", "true,false", "true,true"})
    @DisplayName("색깔 정책과 일치하는 색일 경우 해당 정책을 만족하지 않는다.")
    void Given_ColorPolicy_When_IsSatisfiedWithDifferentColor_Then_False(boolean firstMove, boolean existEnemy) {
        //given
        ColorPolicy policy = new ColorPolicy(Color.WHITE);
        //when, then
        assertThat(policy.isSatisfied(Color.BLACK, firstMove, existEnemy)).isFalse();
    }
}
