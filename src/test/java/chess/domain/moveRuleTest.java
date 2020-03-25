package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class moveRuleTest {

    @Test
    @DisplayName("직선 이동 가능여부 테스트, 통과")
    void validateStraightMove1() {
        Position source = new Position(File.A, Rank.ONE);
        Position target = new Position(File.A, Rank.EIGHT);
        MoveRule.validateStraightMove(source, target);
    }

    @Test
    @DisplayName("직선 이동 가능여부 테스트, 익셉션")
    void validateStraightMove2() {
        Position source = new Position(File.A, Rank.ONE);
        Position target = new Position(File.B, Rank.TWO);
        assertThatThrownBy(() -> MoveRule.validateStraightMove(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}