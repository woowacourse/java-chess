package chess.domain.piece;

import chess.domain.move.enums.DiagonalMove;
import chess.domain.move.enums.HorizontalMove;
import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {

    @Test
    @DisplayName("룩이 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Rook rook = new Rook(Team.WHITE);

        // then
        assertTrue(rook.movable(HorizontalMove.LEFT));
    }

    @Test
    @DisplayName("룩이 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Rook rook = new Rook(Team.WHITE);

        // then
        assertFalse(rook.movable(DiagonalMove.LEFT_UP));
    }

    @Test
    @DisplayName("룩은 끝까지 이동할 수 있다")
    void movableByCount() {
        // given
        final Rook rook = new Rook(Team.WHITE);

        // then
        assertTrue(rook.movableByCount(5));
    }
}
