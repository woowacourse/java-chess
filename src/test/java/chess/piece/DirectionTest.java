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
        assertThat(rightDirection.isHorizontal()).isTrue();
        assertThat(upDirection.isHorizontal()).isFalse();
    }

    @Test
    @DisplayName("수직으로 이동하는지 여부를 반환한다.")
    void isVerticalMovable() {
        // given
        final Direction rightDirection = Direction.RIGHT;
        final Direction upDirection = Direction.UP;

        // when, then
        assertThat(rightDirection.isVertical()).isFalse();
        assertThat(upDirection.isVertical()).isTrue();
    }
}
