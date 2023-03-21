package chess.domain.piece;

import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Direction.LEFT_DOWN;
import static chess.domain.piece.Direction.LEFT_UP_UP;
import static chess.domain.piece.PieceConstants.*;
import static chess.domain.team.Team.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {
    @Test
    @DisplayName("퀸이 이동할 수 있으면 true를 반환한다")
    void movable() {
        // given
        final Queen queen = new Queen(WHITE);

        // then
        assertTrue(queen.movable(LEFT_DOWN, EMPTY));
    }

    @Test
    @DisplayName("퀸이 이동할 수 없으면 false를 반환한다")
    void notMovable() {
        // given
        final Queen queen = new Queen(WHITE);

        // then
        assertFalse(queen.movable(LEFT_UP_UP, EMPTY));
    }

    @Test
    @DisplayName("퀸은 끝까지 이동할 수 있다")
    void movableByCount() {
        // given
        final Queen queen = new Queen(WHITE);

        // then
        assertTrue(queen.movableByCount(5));
    }
}
