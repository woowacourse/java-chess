package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MovementUnitTest {

    @Test
    void 위_계산() {
        assertThat(MovementUnit.direction(0, 3)).isEqualTo(MovementUnit.UP);
    }

    @Test
    void 아래_계산() {
        assertThat(MovementUnit.direction(0, -3)).isEqualTo(MovementUnit.DOWN);
    }

    @Test
    void 오른쪽_계산() {
        assertThat(MovementUnit.direction(3, 0)).isEqualTo(MovementUnit.RIGHT);
    }

    @Test
    void 왼쪽_계산() {
        assertThat(MovementUnit.direction(-3, 0)).isEqualTo(MovementUnit.LEFT);
    }

    @Test
    void 대각선_계산_위_오른쪽() {
        assertThat(MovementUnit.direction(3, 3)).isEqualTo(MovementUnit.UP_RIGHT);
    }

    @Test
    void 대각선_계산_위_왼쪽() {
        assertThat(MovementUnit.direction(-3, 3)).isEqualTo(MovementUnit.UP_LEFT);
    }

    @Test
    void 대각선_계산_아래_오른쪽() {
        assertThat(MovementUnit.direction(3, -3)).isEqualTo(MovementUnit.DOWN_RIGHT);
    }

    @Test
    void 대각선_계산_아래_왼쪽() {
        assertThat(MovementUnit.direction(-3, -3)).isEqualTo(MovementUnit.DOWN_LEFT);
    }

    @Test
    void 나이트_계산_오른쪽1_위2() {
        assertThat(MovementUnit.direction(1, 2)).isEqualTo(MovementUnit.KNIGHT_UP_RIGHT);
    }

    @Test
    void 나이트_계산_오른쪽2_위1() {
        assertThat(MovementUnit.direction(2, 1)).isEqualTo(MovementUnit.KNIGHT_RIGHT_UP);
    }

    @Test
    void 나이트_계산_왼쪽1_위2() {
        assertThat(MovementUnit.direction(-1, 2)).isEqualTo(MovementUnit.KNIGHT_UP_LEFT);
    }

    @Test
    void 나이트_계산_왼쪽2_위1() {
        assertThat(MovementUnit.direction(-2, 1)).isEqualTo(MovementUnit.KNIGHT_LEFT_UP);
    }

    @Test
    void 나이트_계산_오른쪽1_아래2() {
        assertThat(MovementUnit.direction(1, -2)).isEqualTo(MovementUnit.KNIGHT_DOWN_RIGHT);
    }

    @Test
    void 나이트_계산_오른쪽2_아래1() {
        assertThat(MovementUnit.direction(2, -1)).isEqualTo(MovementUnit.KNIGHT_RIGHT_DOWN);
    }

    @Test
    void 나이트_계산_왼쪽1_아래2() {
        assertThat(MovementUnit.direction(-1, -2)).isEqualTo(MovementUnit.KNIGHT_DOWN_LEFT);
    }

    @Test
    void 나이트_계산_왼쪽2_아래1() {
        assertThat(MovementUnit.direction(-2, -1)).isEqualTo(MovementUnit.KNIGHT_LEFT_DOWN);
    }

    @Test
    void NOT_MOVE_계산() {
        assertThat(MovementUnit.direction(-3, -1)).isEqualTo(MovementUnit.NOT_MOVE);
    }
}