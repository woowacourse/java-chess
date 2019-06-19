package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovementUnitTest {
    @Test
    void 대각선_계산() {
        Spot spotA = Spot.valueOf(1, 2);
        Spot spotB = Spot.valueOf(4, 5);
        assertThat(MovementUnit.direction(spotA, spotB)).isEqualTo(MovementUnit.DIAGNOAL);
    }

    @Test
    void 가로_계산() {
        Spot spotA = Spot.valueOf(1, 2);
        Spot spotB = Spot.valueOf(4, 2);
        assertThat(MovementUnit.direction(spotA, spotB)).isEqualTo(MovementUnit.UP);
    }

    @Test
    void 세로_계산() {
        Spot spotA = Spot.valueOf(1, 2);
        Spot spotB = Spot.valueOf(1, 5);
        assertThat(MovementUnit.direction(spotA, spotB)).isEqualTo(MovementUnit.RIGHT);
    }

    @Test
    void 나이트_계산1() {
        Spot spotA = Spot.valueOf(1, 2);
        Spot spotB = Spot.valueOf(2, 4);
        assertThat(MovementUnit.direction(spotA, spotB)).isEqualTo(MovementUnit.KNIGHT_MOVE_TWO);
    }

    @Test
    void 나이트_계산2() {
        Spot spotA = Spot.valueOf(1, 2);
        Spot spotB = Spot.valueOf(3, 3);
        assertThat(MovementUnit.direction(spotA, spotB)).isEqualTo(MovementUnit.KNIGHT_MOVE_ONE);
    }

    @Test
    void 움직임_예외1() {
        Spot spotA = Spot.valueOf(1, 2);
        Spot spotB = Spot.valueOf(4, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            MovementUnit.direction(spotA, spotB);
        });
    }

    @Test
    void 움직임_예외2() {
        Spot spotA = Spot.valueOf(1, 2);
        Spot spotB = Spot.valueOf(1, 2);
        assertThrows(IllegalArgumentException.class, () -> {
            MovementUnit.direction(spotA, spotB);
        });
    }


}