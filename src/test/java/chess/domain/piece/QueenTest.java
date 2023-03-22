package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.color.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {
    @Test
    @DisplayName("퀸이 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Queen queen = new Queen(Color.WHITE);

        // then
        assertTrue(queen.movable(Direction.LEFT_DOWN));
    }

    @Test
    @DisplayName("퀸이 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Queen queen = new Queen(Color.WHITE);

        // then
        assertFalse(queen.movable(Direction.KNIGHT_LEFT_UP));
    }

    @Test
    @DisplayName("퀸은 끝까지 이동할 수 있다")
    void movableByCount() {
        // given
        final Queen queen = new Queen(Color.WHITE);

        // then
        assertTrue(queen.movableByCount(5));
    }
}
