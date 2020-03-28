package chess.domain.RuleStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightRuleStrategyTest {

    @Test
    void KnightRuleStrategy_GenerateInstance() {
        assertThat(new KnightRuleStrategy()).isInstanceOf(KnightRuleStrategy.class);
    }

    @ParameterizedTest
    @NullSource
    void validate_NullSourcePosition_ExceptionThrown(Position sourcePosition) {
        Position targetPosition = Position.of("b1");

        assertThatThrownBy(() -> new KnightRuleStrategy().canMove(sourcePosition, targetPosition))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("소스 위치가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @NullSource
    void validate_NullTargetPosition_ExceptionThrown(Position targetPosition) {
        Position sourcePosition = Position.of("b1");

        assertThatThrownBy(() -> new KnightRuleStrategy().canMove(sourcePosition, targetPosition))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("타겟 위치가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"b3", "b5", "c2", "e2", "f3", "f5", "c6", "e6"})
    void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
        KnightRuleStrategy knightRuleStrategy = new KnightRuleStrategy();
        Position sourcePosition = Position.of("d4");

        assertThat(knightRuleStrategy.canMove(sourcePosition, targetPosition)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"d5", "e4", "d3", "c4"})
    void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
        KnightRuleStrategy knightRuleStrategy = new KnightRuleStrategy();
        Position sourcePosition = Position.of("d4");

        assertThat(knightRuleStrategy.canMove(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    void canLeap_ReturnTrue() {
        assertThat(new KnightRuleStrategy().canLeap()).isTrue();
    }

}