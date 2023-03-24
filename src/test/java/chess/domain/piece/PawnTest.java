package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.Direction.DOWN;
import static chess.domain.piece.Direction.LEFT_DOWN;
import static chess.domain.piece.Direction.RIGHT_DOWN;
import static chess.domain.piece.Direction.UP;
import static chess.domain.piece.PieceConstants.BLACK_PAWN;
import static chess.domain.piece.PieceConstants.EMPTY;
import static chess.domain.piece.PieceConstants.WHITE_PAWN;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {

    @Test
    @DisplayName("폰이 검은말(대문자)일 때, 아래 방향이고, 아래가 빈 공간이면 이동할 수 있다")
    void movableDownIfBlack() {
        // given
        final Pawn pawn = BLACK_PAWN;

        // then
        assertTrue(pawn.movable(DOWN, EMPTY));
    }

    @Test
    @DisplayName("폰이 흰말(소문자)일 때, 위 방향이고, 위가 빈 공간이면 이동할 수 있다")
    void movableUpIfWhite() {
        // given
        final Pawn pawn = WHITE_PAWN;

        // then
        assertTrue(pawn.movable(UP, EMPTY));
    }

    @Test
    @DisplayName("폰은 처음에 두 칸까지 이동할 수 있다.")
    void movableByCount_firstMove() {
        // given
        final Pawn pawn = BLACK_PAWN;

        // then
        assertTrue(pawn.movableByCount(2));
    }

    @Test
    @DisplayName("폰은 두 번째 이동부터 두 칸 이상을 이동할 수 없다.")
    void notMovableByCount_secondMove() {
        // given
        final Pawn pawn = BLACK_PAWN;

        // when
        pawn.movableByCount(2);

        // then
        assertFalse(pawn.movableByCount(2));
    }

    @Test
    @DisplayName("폰은 두 번째 이동부터 한 칸만 이동할 수 있다.")
    void movableByCount_secondMove() {
        // given
        final Pawn pawn = BLACK_PAWN;

        // when
        pawn.movableByCount(2);

        // then
        assertTrue(pawn.movableByCount(1));
    }

    @Test
    @DisplayName("폰은 자신의 이동방향의 대각선 방향에 상대 기물이 있으면 공격할 수 있다")
    void isAttackIfDiagonalExistOpponentPiece() {
        // given
        final Pawn pawn = BLACK_PAWN;

        // when
        final boolean actual1 = pawn.isAttack(LEFT_DOWN, WHITE_PAWN);
        final boolean actual2 = pawn.isAttack(RIGHT_DOWN, WHITE_PAWN);

        // then
        assertTrue(actual1);
        assertTrue(actual2);
    }

    @Test
    @DisplayName("폰은 자신의 이동방향의 대각선 방향에 같은 팀 기물이나, 기물이 없으면 공격할 수 없다")
    void isNotAttackIfDiagonalExistOpponentPiece() {
        // given
        final Pawn pawn = BLACK_PAWN;

        // when
        final boolean actual1 = pawn.isAttack(LEFT_DOWN, BLACK_PAWN);
        final boolean actual2 = pawn.isAttack(RIGHT_DOWN, EMPTY);

        // then
        assertFalse(actual1);
        assertFalse(actual2);
    }
}
