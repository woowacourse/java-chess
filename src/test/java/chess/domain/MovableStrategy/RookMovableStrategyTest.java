package chess.domain.MovableStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookMovableStrategyTest {
    @Test
    void RookMovableStrategy_GenerateInstance() {
        assertThat(new RookMovableStrategy()).isInstanceOf(RookMovableStrategy.class);
    }

    @Test
    void canMove_CanMovableSourceAndTarget_ReturnTrue() {
        MovableStrategy movableStrategy = new RookMovableStrategy();
        Position source = Position.of("b3");
        Position target = Position.of("b8");

        assertThat(movableStrategy.canMove(source, target)).isTrue();
    }
}