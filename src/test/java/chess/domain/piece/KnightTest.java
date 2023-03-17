package chess.domain.piece;

import chess.domain.move.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
    @Test
    @DisplayName("나이트가 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Knight knight = new Knight(true);

        // then
        assertTrue(knight.movable(Direction.KNIGHT_LEFT_UP));
    }

    @Test
    @DisplayName("나이트가 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Knight knight = new Knight(true);

        // then
        assertFalse(knight.movable(Direction.DOWN));
    }

    @Test
    @DisplayName("나이트는 항상 한 칸 이상 이동한다")
    void movableByCount() {
        // given
        final Knight knight = new Knight(true);

        // then
        assertTrue(knight.movableByCount(5));
    }
}
