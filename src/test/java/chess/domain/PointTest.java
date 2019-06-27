package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    @Test
    void 포인트_생성() {
        assertDoesNotThrow(() -> new Point("a1"));
    }

    @Test
    void 포인트_유효성_검사() {
        assertThrows(IllegalArgumentException.class, () -> new Point("a9"));
    }

    @Test
    void 두점_사이_거리구하기() {
        Point start = new Point("a1");
        Point end = new Point("b3");
        assertThat(start.calDistance(end)).isEqualTo(5);
    }

    @Test
    void X_Y_둘중하나_좌표_같음() {
        Point start = new Point("a1");
        Point end = new Point("b1");
        assertTrue(start.isSameCoordinate(end));
    }

    @Test
    void X_Y_좌표_다름() {
        Point start = new Point("a1");
        Point end = new Point("c2");
        assertFalse(start.isSameCoordinate(end));
    }

    @Test
    void X_Y_둘다_좌표_같음() {
        Point start = new Point("a1");
        Point end = new Point("a1");
        assertFalse(start.isSameCoordinate(end));
    }

    @Test
    void 방향벡터_구하기() {
        Point start = new Point("a1");
        Point end = new Point("a2");
        assertThat(start.makeVector(end)).isEqualTo(new Point(0, 1));
    }

    @Test
    void 방향_더하기() {
        Point point = new Point("b2");
        assertThat(point.move(Direction.NORTH)).isEqualTo(new Point("b3"));
        assertThat(point.move(Direction.SOUTH)).isEqualTo(new Point("b1"));
        assertThat(point.move(Direction.EAST)).isEqualTo(new Point("c2"));
        assertThat(point.move(Direction.WEST)).isEqualTo(new Point("a2"));
    }

    @Test
    void 경계선_이탈() {
        Point point = new Point("a1");
        assertThrows(IllegalArgumentException.class, () -> point.move(Direction.SOUTH));
    }
}
