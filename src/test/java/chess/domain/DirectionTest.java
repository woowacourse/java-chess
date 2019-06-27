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

    @Test
    void vector_둘_중하나가_0일때() {
        Direction direction = new Direction(0, 7);
        assertEquals(new Direction(0, 1), direction.vector());
    }

    @Test
    void vector_둘다_0이_아닐때() {
        Direction direction = new Direction(-7, 7);
        assertEquals(new Direction(-1, 1), direction.vector());
    }

    @Test
    void vector_둘다_0이_아니지만_절대값이_다를때() {
        Direction direction = new Direction(-6, 7);
        assertThrows(IllegalArgumentException.class, direction::vector);
    }
}