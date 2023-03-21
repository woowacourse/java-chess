package chess.domain.piece;

import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Direction.LEFT_UP;
import static chess.domain.piece.PieceConstants.*;
import static chess.domain.team.Team.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmptyTest {

    @Test
    @DisplayName("빈 공간은 이동할 수 없다")
    void notMovable() {
        // given
        final Empty empty = new Empty(NONE);

        // then
        assertFalse(empty.movable(LEFT_UP, EMPTY));
    }

    @Test
    @DisplayName("빈 공간은 한 칸도 이동할 수 없다")
    void neverMovable() {
        // given
        final Empty empty = new Empty(NONE);

        // then
        assertFalse(empty.movableByCount(1));
    }
}
