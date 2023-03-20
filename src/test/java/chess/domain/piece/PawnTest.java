package chess.domain.piece;

import chess.domain.move.enums.VerticalMove;
import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {

    @Test
    @DisplayName("폰이 검은말(대문자)이라면 아래로만 이동할 수 있다")
    void movableDownIfBlack() {
        // given
        final Pawn pawn = new Pawn(Team.BLACK);

        // then
        assertTrue(pawn.movable(VerticalMove.DOWN));
    }

    @Test
    @DisplayName("폰이 흰말(소문자)이라면 위로만 이동할 수 있다")
    void movableUpIfWhite() {
        // given
        final Pawn pawn = new Pawn(Team.WHITE);

        // then
        assertTrue(pawn.movable(VerticalMove.UP));
    }

    @Test
    @DisplayName("폰은 처음에 두 칸까지 이동할 수 있다.")
    void movableByCount_firstMove() {
        // given
        final Pawn pawn = new Pawn(Team.BLACK);

        // then
        assertTrue(pawn.movableByCount(2));
    }

    @Test
    @DisplayName("폰은 두 번째 이동부터 두 칸 이상을 이동할 수 없다.")
    void notMovableByCount_secondMove() {
        // given
        final Pawn pawn = new Pawn(Team.BLACK);

        // when
        pawn.movableByCount(2);

        // then
        assertFalse(pawn.movableByCount(2));
    }

    @Test
    @DisplayName("폰은 두 번째 이동부터 한 칸만 이동할 수 있다.")
    void movableByCount_secondMove() {
        // given
        final Pawn pawn = new Pawn(Team.BLACK);

        // when
        pawn.movableByCount(2);

        // then
        assertTrue(pawn.movableByCount(1));
    }
}
