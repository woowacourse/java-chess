package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovementUnitTest {
    @Test
    void 방향_계산() {
        Spot spotA = Spot.valueOf(1, 2);
        Spot spotB = Spot.valueOf(4, 5);
        assertThat(MovementUnit.direction(spotA, spotB)).isEqualTo(MovementUnit.DIAGNOAL);
    }
}