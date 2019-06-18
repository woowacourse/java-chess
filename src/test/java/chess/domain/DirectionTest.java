package chess.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DirectionTest {

    @Test
    void 생성_테스트() {
        Direction direction = new Direction(1, 3);
        assertEquals(new Direction(1, 3), direction);
    }

    @Test
    void 범위_벗어남_테스트() {
        assertThrows(IllegalArgumentException.class, () -> new Direction(-8, 1));
    }

    @Test
    void 범위_벗어남_테스트2() {
        assertThrows(IllegalArgumentException.class, () -> new Direction(-7, 8));
    }
}