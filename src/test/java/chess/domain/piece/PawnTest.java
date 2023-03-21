package chess.domain.piece;

import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Direction.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {

    @Test
    @DisplayName("폰이 검은말(대문자)이라면 아래로만 이동할 수 있다")
    void movableDownIfBlack() {
        // given
        final Pawn pawn = new Pawn(Team.BLACK);

        // then
        assertTrue(pawn.movable(DOWN));
    }

    @Test
    @DisplayName("폰이 흰말(소문자)이라면 위로만 이동할 수 있다")
    void movableUpIfWhite() {
        // given
        final Pawn pawn = new Pawn(Team.WHITE);

        // then
        assertTrue(pawn.movable(UP));
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

    @Test
    @DisplayName("폰은 자신의 이동방향의 대각선 방향에 상대 기물이 있으면 공격할 수 있다")
    void isAttackIfDiagonalExistOpponentPiece() {
        // given
        final Pawn pawn = new Pawn(Team.BLACK);

        // when
        final boolean actual1 = pawn.isAttack(LEFT_DOWN, Team.WHITE);
        final boolean actual2 = pawn.isAttack(RIGHT_DOWN, Team.WHITE);

        // then
        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    @DisplayName("폰은 자신의 이동방향의 대각선 방향에 같은 팀 기물이나, 기물이 없으면 공격할 수 없다")
    void isNotAttackIfDiagonalExistOpponentPiece() {
        // given
        final Pawn pawn = new Pawn(Team.BLACK);

        // when
        final boolean actual1 = pawn.isAttack(LEFT_DOWN, Team.BLACK);
        final boolean actual2 = pawn.isAttack(RIGHT_DOWN, Team.NONE);

        // then
        assertFalse(actual1);
        assertFalse(actual2);
    }
}
