package domain.piece.pawn;

import domain.piece.move.Situation;
import domain.piece.move.Coordinate;
import domain.piece.Color;
import domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PawnTest {

    @Test
    @DisplayName("폰은 기본 상태를 가진다")
    void propertyTest() {
        Piece whitePawn = new WhitePawn(Color.WHITE);
        Piece blackPawn = new BlackPawn(Color.BLACK);

        assertThat(whitePawn.canJump()).isFalse();
        assertThat(whitePawn.isPawn()).isTrue();
        assertThat(whitePawn.getPoint()).isEqualTo(1);
        assertThat(whitePawn.isKing()).isFalse();
        assertThat(whitePawn.hasSameColorWith(Color.WHITE)).isTrue();

        assertThat(blackPawn.canJump()).isFalse();
        assertThat(blackPawn.isPawn()).isTrue();
        assertThat(blackPawn.getPoint()).isEqualTo(1);
        assertThat(blackPawn.isKing()).isFalse();
        assertThat(blackPawn.hasSameColorWith(Color.BLACK)).isTrue();
    }

    @Test
    @DisplayName("화이트 폰은 위로 한 칸 이동할 수 있다")
    void isReachableByRuleUp() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate endCoordinate = new Coordinate(2, 0);
        Pawn pawn = new WhitePawn(Color.WHITE);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @Test
    @DisplayName("화이트 폰은 위로 두 칸 이동할 수 있다")
    void isReachableByRuleUpTwo() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate endCoordinate = new Coordinate(3, 0);
        Pawn pawn = new WhiteInitPawn(Color.WHITE);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @Test
    @DisplayName("화이트 폰은 위로 세 칸 이동할 수 없다")
    void isReachableByRuleUpThree() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate endCoordinate = new Coordinate(4, 0);
        Pawn pawn = new WhitePawn(Color.WHITE);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
        assertThat(pawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();

    }

    @Test
    @DisplayName("화이트 폰은 아래로 한 칸 이동할 수 없다")
    void isReachableByRuleCantDown() {
        Coordinate startCoordinate = new Coordinate(2, 0);
        Coordinate endCoordinate = new Coordinate(1, 0);
        Pawn pawn = new WhitePawn(Color.WHITE);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
    }

    @Test
    @DisplayName("블랙 폰은 아래로 한 칸 이동할 수 있다")
    void isReachableByRuleDown() {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(5, 0);
        Pawn pawn = new BlackPawn(Color.BLACK);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @Test
    @DisplayName("블랙 폰은 아래로 두 칸 이동할 수 있다")
    void isReachableByRuleDownTwo() {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(4, 0);
        Pawn pawn = new BlackInitPawn(Color.BLACK);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isTrue();
    }

    @Test
    @DisplayName("블랙 폰은 아래로 세 칸 이동할 수 없다")
    void isReachableByRuleDownThree() {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(3, 0);
        Pawn pawn = new BlackPawn(Color.BLACK);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
        assertThat(pawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
    }

    @Test
    @DisplayName("블랙 폰은 위로 한 칸 이동할 수 없다")
    void isReachableByRuleCantUp() {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(7, 0);
        Pawn pawn = new BlackPawn(Color.BLACK);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
    }

    @Test
    @DisplayName("블랙 폰은 가로로 이동할 수 없다")
    void isReachableByRuleCantStraight() {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(6, 1);
        Pawn blackPawn = new BlackPawn(Color.BLACK);
        Pawn whitePawn = new WhitePawn(Color.WHITE);

        assertThat(blackPawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
        assertThat(whitePawn.isMovable(startCoordinate, endCoordinate, Situation.NEUTRAL)).isFalse();
    }
}
