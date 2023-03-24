package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Direction.DOWN;
import static chess.domain.piece.Direction.LEFT_UP_UP;
import static chess.domain.piece.PieceConstants.EMPTY;
import static chess.domain.team.Team.WHITE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {
    @Test
    @DisplayName("나이트가 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Knight knight = new Knight(WHITE);

        // then
        assertTrue(knight.movable(LEFT_UP_UP, EMPTY));
    }

    @Test
    @DisplayName("나이트가 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Knight knight = new Knight(WHITE);

        // then
        assertFalse(knight.movable(DOWN, EMPTY));
    }

    @Test
    @DisplayName("나이트는 항상 한 칸 이상 이동한다")
    void movableByCount() {
        // given
        final Knight knight = new Knight(WHITE);

        // then
        assertTrue(knight.movableByCount(5));
    }
}
