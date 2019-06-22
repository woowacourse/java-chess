package chess.domain.direction.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MoveStrategyTest {
    @Test
    void 오직_비어있을때만_가능_테스트() {
        assertThat(MoveStrategy.ONLY_EMPTY.canMove(TargetStatus.EMPTY)).isTrue();
    }
}