package chess.domain.MovableStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenMovableStrategyTest {
    @Test
    void QueenMovableStrategy_GenerateInstance() {
        assertThat(new QueenMovableStrategy()).isInstanceOf(QueenMovableStrategy.class);
    }

    @Test
    void canMove_CanMovableSourceAndTarget_ReturnTrue() {
        MovableStrategy movableStrategy = new QueenMovableStrategy();
        Position source = Position.of("b3");
        Position target = Position.of("e6");

        assertThat(movableStrategy.canMove(source, target)).isTrue();
    }
}