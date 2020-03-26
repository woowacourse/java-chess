package chess.domain.MovableStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightMovableStrategyTest {
    @Test
    void KnightMovableStrategy_GenerateInstance() {
        assertThat(new KnightMovableStrategy()).isInstanceOf(KnightMovableStrategy.class);
    }

    @ParameterizedTest
    @NullSource
    void canMove_NullSource_ExceptionThrown(Position position) {
        assertThatThrownBy(() -> new KnightMovableStrategy().canMove(position, Position.of("b1")))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이동할 소스가 존재하지 않습니다.");
    }

    @ParameterizedTest
    @NullSource
    void canMove_NullTarget_ExceptionThrown(Position position) {
        assertThatThrownBy(() -> new KnightMovableStrategy().canMove(Position.of("b1"), position))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이동할 타겟이 존재하지 않습니다.");
    }

    @Test
    void canMove_CanMovableSourceAndTarget_ReturnTrue() {
        MovableStrategy movableStrategy = new KnightMovableStrategy();
        Position source = Position.of("b3");
        Position target = Position.of("d4");

        assertThat(movableStrategy.canMove(source, target)).isTrue();
    }

    @Test
    void canLeap_ReturnTrue() {
        assertThat(new KnightMovableStrategy().canLeap()).isTrue();
    }
}