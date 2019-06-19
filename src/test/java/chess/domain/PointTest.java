package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
