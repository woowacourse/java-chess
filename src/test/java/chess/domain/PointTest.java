package chess.domain;

import org.junit.jupiter.api.Test;

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
}
