package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.color.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {
    @Test
    @DisplayName("킹이 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final King king = new King(Color.WHITE);

        // then
        assertTrue(king.movable(Direction.LEFT_UP));
    }

    @Test
    @DisplayName("킹이 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final King king = new King(Color.WHITE);

        // then
        assertFalse(king.movable(Direction.KNIGHT_DOWN_LEFT));
    }

    @Test
    @DisplayName("킹은 한 칸만 이동할 수 있다")
    void movableByCount() {
        // given
        final King king = new King(Color.WHITE);

        // then
        assertTrue(king.movableByCount(0));
    }

    @Test
    @DisplayName("킹은 두 칸 이상 이동할 수 없다")
    void notMovableByCountOver2() {
        // given
        final King king = new King(Color.WHITE);

        // then
        assertFalse(king.movableByCount(2));
    }
}
