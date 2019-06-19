package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovementUnitTest {
    @Test
    void 대각선_계산() {
        assertThat(MovementUnit.direction(3, 3)).isEqualTo(MovementUnit.DIAGNOAL);
    }

    @Test
    void 가로_계산() {
        assertThat(MovementUnit.direction(3, 0)).isEqualTo(MovementUnit.UP);
    }

    @Test
    void 세로_계산() {
        assertThat(MovementUnit.direction(0, 3)).isEqualTo(MovementUnit.RIGHT);
    }

    @Test
    void 나이트_계산1() {
        assertThat(MovementUnit.direction(1, 2)).isEqualTo(MovementUnit.KNIGHT_MOVE_TWO);
    }

    @Test
    void 나이트_계산2() {
        assertThat(MovementUnit.direction(2, 1)).isEqualTo(MovementUnit.KNIGHT_MOVE_ONE);
    }

    @Test
    void 움직임_예외1() {
        assertThrows(IllegalArgumentException.class, () -> {
            MovementUnit.direction(1, 4);
        });
    }

    @Test
    void 움직임_예외2() {
        assertThrows(IllegalArgumentException.class, () -> {
            MovementUnit.direction(0, 0);
        });
    }


}