package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.color.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest {

    @Test
    @DisplayName("비숍이 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Bishop bishop = new Bishop(Color.WHITE);

        // then
        assertTrue(bishop.movable(Direction.LEFT_UP));
    }

    @Test
    @DisplayName("비숍이 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Bishop bishop = new Bishop(Color.WHITE);

        // then
        assertFalse(bishop.movable(Direction.DOWN));
    }

    @Test
    @DisplayName("비숍은 끝까지 이동할 수 있다")
    void movableByCount() {
        // given
        final Bishop bishop = new Bishop(Color.WHITE);

        // then
        assertTrue(bishop.movableByCount(5));
    }
}
