package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Direction.DOWN;
import static chess.domain.piece.Direction.LEFT_UP;
import static chess.domain.piece.PieceConstants.EMPTY;
import static chess.domain.team.Team.WHITE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest {

    @Test
    @DisplayName("비숍이 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Bishop bishop = new Bishop(WHITE);

        // then
        assertTrue(bishop.movable(LEFT_UP, EMPTY));
    }

    @Test
    @DisplayName("비숍이 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Bishop bishop = new Bishop(WHITE);

        // then
        assertFalse(bishop.movable(DOWN, EMPTY));
    }

    @Test
    @DisplayName("비숍은 끝까지 이동할 수 있다")
    void movableByCount() {
        // given
        final Bishop bishop = new Bishop(WHITE);

        // then
        assertTrue(bishop.movableByCount(5));
    }
}
