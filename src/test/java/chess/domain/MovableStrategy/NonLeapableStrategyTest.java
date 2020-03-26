package chess.domain.MovableStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NonLeapableStrategyTest {
    @ParameterizedTest
    @NullSource
    void canMove_NullSource_ExceptionThrown(Position position) {
        NonLeapableStrategy nonLeapableStrategy = new QueenMovableStrategy();

        assertThatThrownBy(() -> nonLeapableStrategy.canMove(position, Position.of("b1")))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이동할 소스가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @NullSource
    void canMove_NullTarget_ExceptionThrown(Position position) {
        NonLeapableStrategy nonLeapableStrategy = new QueenMovableStrategy();

        assertThatThrownBy(() -> nonLeapableStrategy.canMove(Position.of("b1"), position))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이동할 타겟이 존재하지 않습니다.");
    }

    @Test
    void canLeap_ReturnFalse() {
        assertThat(new QueenMovableStrategy().canLeap()).isFalse();
    }
}