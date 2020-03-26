package chess.domain.MovableStrategy;

import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopMovableStrategyTest {
    @Test
    void BishopMovableStrategy_GenerateInstance() {
        assertThat(new BishopMovableStrategy()).isInstanceOf(BishopMovableStrategy.class);
    }

    @Test
    void canMove_CanMovableSourceAndTarget_ReturnTrue() {
        MovableStrategy movableStrategy = new BishopMovableStrategy();
        Position source = Position.of("b3");
        Position target = Position.of("c4");

        assertThat(movableStrategy.canMove(source, target)).isTrue();
    }
}