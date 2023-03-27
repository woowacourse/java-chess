package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    @DisplayName("x좌표를 받아서 다음 x 좌표를 반환한다.")
    void getNextXPoint() {
        // given
        final Direction upDirection = Direction.UP;

        // when, then
        assertThat(upDirection.getNextXPoint(2)).isEqualTo(2);
    }

    @Test
    @DisplayName("y좌표를 받아서 다음 y 좌표를 반환한다.")
    void getNextYPoint() {
        // given
        final Direction upDirection = Direction.UP;

        // when, then
        assertThat(upDirection.getNextYPoint(2)).isEqualTo(3);
    }

    @Test
    @DisplayName("수평으로 이동하는지 여부를 반환한다.")
    void isHorizontalMovable() {
        // given
        final Direction rightDirection = Direction.RIGHT;
        final Direction upDirection = Direction.UP;

        // when, then
        assertThat(rightDirection.isHorizontalMovable()).isTrue();
        assertThat(upDirection.isHorizontalMovable()).isFalse();
    }

    @Test
    @DisplayName("수직으로 이동하는지 여부를 반환한다.")
    void isVerticalMovable() {
        // given
        final Direction rightDirection = Direction.RIGHT;
        final Direction upDirection = Direction.UP;

        // when, then
        assertThat(rightDirection.isVerticalMovable()).isFalse();
        assertThat(upDirection.isVerticalMovable()).isTrue();
    }

    @Test
    @DisplayName("대각선으로 이동하는지 여부를 반환한다.")
    void isDiagonalMovable() {
        // given
        final Direction upRightDirection = Direction.UP_RIGHT;
        final Direction upLeftDirection = Direction.UP_LEFT;
        final Direction downLeftDirection = Direction.DOWN_LEFT;
        final Direction downRightDirection = Direction.DOWN_RIGHT;
        final Direction upDirection = Direction.UP;


        // when, then
        assertThat(upRightDirection.isDiagonalMovable()).isTrue();
        assertThat(upLeftDirection.isDiagonalMovable()).isTrue();
        assertThat(downLeftDirection.isDiagonalMovable()).isTrue();
        assertThat(downRightDirection.isDiagonalMovable()).isTrue();
        assertThat(upDirection.isDiagonalMovable()).isFalse();
    }
}
