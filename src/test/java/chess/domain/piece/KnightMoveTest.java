package chess.domain.piece;

import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Direction.DOWN;
import static chess.domain.piece.Direction.LEFT_UP_UP;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightMoveTest {
    @Test
    @DisplayName("나이트가 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Knight knight = new Knight(Team.WHITE);

        // then
        assertTrue(knight.movable(LEFT_UP_UP));
    }

    @Test
    @DisplayName("나이트가 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Knight knight = new Knight(Team.WHITE);

        // then
        assertFalse(knight.movable(DOWN));
    }

    @Test
    @DisplayName("나이트는 항상 한 칸 이상 이동한다")
    void movableByCount() {
        // given
        final Knight knight = new Knight(Team.WHITE);

        // then
        assertTrue(knight.movableByCount(5));
    }
}
