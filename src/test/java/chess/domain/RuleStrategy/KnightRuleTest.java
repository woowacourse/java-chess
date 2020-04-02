package chess.domain.RuleStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightRuleTest {

    @Test
    void KnightRuleStrategy_GenerateInstance() {
        assertThat(new KnightRule()).isInstanceOf(KnightRule.class);
    }

    @ParameterizedTest
    @NullSource
    void validate_NullSourcePosition_ExceptionThrown(Position sourcePosition) {
        Position targetPosition = Position.of("b1");

        assertThatThrownBy(() -> new KnightRule().canMove(sourcePosition, targetPosition))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("소스 위치가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @NullSource
    void validate_NullTargetPosition_ExceptionThrown(Position targetPosition) {
        Position sourcePosition = Position.of("b1");

        assertThatThrownBy(() -> new KnightRule().canMove(sourcePosition, targetPosition))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("타겟 위치가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"b3", "b5", "c2", "e2", "f3", "f5", "c6", "e6"})
    void canMove_MovableSourcePositionAndTargetPosition_ReturnTrue(Position targetPosition) {
        KnightRule knightRuleStrategy = new KnightRule();
        Position sourcePosition = Position.of("d4");

        assertThat(knightRuleStrategy.canMove(sourcePosition, targetPosition)).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"d5", "e4", "d3", "c4"})
    void canMove_NonMovableSourcePositionAndTargetPosition_ReturnFalse(Position targetPosition) {
        KnightRule knightRuleStrategy = new KnightRule();
        Position sourcePosition = Position.of("d4");

        assertThat(knightRuleStrategy.canMove(sourcePosition, targetPosition)).isFalse();
    }

    @Test
    void canLeap_ReturnTrue() {
        assertThat(new KnightRule().canLeap()).isTrue();
    }

}