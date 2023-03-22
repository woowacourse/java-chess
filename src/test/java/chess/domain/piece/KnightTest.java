package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.color.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {
    @Test
    @DisplayName("나이트가 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Knight knight = new Knight(Color.WHITE);

        // then
        assertTrue(knight.movable(Direction.KNIGHT_LEFT_UP));
    }

    @Test
    @DisplayName("나이트가 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Knight knight = new Knight(Color.WHITE);

        // then
        assertFalse(knight.movable(Direction.DOWN));
    }

    @Test
    @DisplayName("나이트는 항상 한 칸 이상 이동한다")
    void movableByCount() {
        // given
        final Knight knight = new Knight(Color.WHITE);

        // then
        assertTrue(knight.movableByCount(5));
    }
}
