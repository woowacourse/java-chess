package chess.domain.piece;

import chess.domain.move.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {

    @Test
    @DisplayName("룩이 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Rook rook = new Rook(true);

        // then
        assertTrue(rook.movable(Direction.LEFT));
    }

    @Test
    @DisplayName("룩이 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Rook rook = new Rook(true);

        // then
        assertFalse(rook.movable(Direction.LEFT_UP));
    }

    @Test
    @DisplayName("룩은 끝까지 이동할 수 있다")
    void movableByCount() {
        // given
        final Rook rook = new Rook(true);

        // then
        assertTrue(rook.movableByCount(5));
    }
}
