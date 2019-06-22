package chess.domain.piece;

import chess.domain.Spot;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmptyTest {
    @Test
    void 움직임_불가() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 5);

        Empty empty = Empty.getInstance();
        assertThat(empty.isMovable(startSpot, endSpot)).isFalse();
    }
}