package chess.domain.piece;

import chess.domain.move.enums.DiagonalMove;
import chess.domain.move.enums.KnightMove;
import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {
    @Test
    @DisplayName("퀸이 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Queen queen = new Queen(Team.WHITE);

        // then
        assertTrue(queen.movable(DiagonalMove.LEFT_DOWN));
    }

    @Test
    @DisplayName("퀸이 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Queen queen = new Queen(Team.WHITE);

        // then
        assertFalse(queen.movable(KnightMove.LEFT_UP_UP));
    }

    @Test
    @DisplayName("퀸은 끝까지 이동할 수 있다")
    void movableByCount() {
        // given
        final Queen queen = new Queen(Team.WHITE);

        // then
        assertTrue(queen.movableByCount(5));
    }
}
