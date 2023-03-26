package domain.piece.pawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.move.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

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
        assertThat(whitePawn.getColor() == Color.WHITE).isTrue();

        assertThat(blackPawn.canJump()).isFalse();
        assertThat(blackPawn.isPawn()).isTrue();
        assertThat(blackPawn.getPoint()).isEqualTo(1);
        assertThat(blackPawn.isKing()).isFalse();
        assertThat(blackPawn.getColor() == Color.BLACK).isTrue();
    }

    @Test
    @DisplayName("화이트 폰은 위로 한 칸 이동할 수 있다")
    void isReachableByRuleUp() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate endCoordinate = new Coordinate(2, 0);
        Pawn pawn = new WhitePawn(Color.WHITE);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("화이트 폰은 위로 두 칸 이동할 수 있다")
    void isReachableByRuleUpTwo() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate endCoordinate = new Coordinate(3, 0);
        Pawn pawn = new WhitePawn(Color.WHITE);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("화이트 폰은 위로 세 칸 이동할 수 없다")
    void isReachableByRuleUpThree() {
        Coordinate startCoordinate = new Coordinate(1, 0);
        Coordinate endCoordinate = new Coordinate(4, 0);
        Pawn pawn = new WhitePawn(Color.WHITE);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate)).isFalse();
        assertThat(pawn.isMovable(startCoordinate, endCoordinate)).isFalse();

    }

    @Test
    @DisplayName("화이트 폰은 아래로 한 칸 이동할 수 없다")
    void isReachableByRuleCantDown() {
        Coordinate startCoordinate = new Coordinate(2, 0);
        Coordinate endCoordinate = new Coordinate(1, 0);
        Pawn pawn = new WhitePawn(Color.WHITE);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate)).isFalse();
    }

    @Test
    @DisplayName("블랙 폰은 아래로 한 칸 이동할 수 있다")
    void isReachableByRuleDown() {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(5, 0);
        Pawn pawn = new BlackPawn(Color.BLACK);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("블랙 폰은 아래로 두 칸 이동할 수 있다")
    void isReachableByRuleDownTwo() {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(4, 0);
        Pawn pawn = new BlackPawn(Color.BLACK);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("블랙 폰은 아래로 세 칸 이동할 수 없다")
    void isReachableByRuleDownThree() {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(3, 0);
        Pawn pawn = new BlackPawn(Color.BLACK);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate)).isFalse();
        assertThat(pawn.isMovable(startCoordinate, endCoordinate)).isFalse();
    }

    @Test
    @DisplayName("블랙 폰은 위로 한 칸 이동할 수 없다")
    void isReachableByRuleCantUp() {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(7, 0);
        Pawn pawn = new BlackPawn(Color.BLACK);

        assertThat(pawn.isMovable(startCoordinate, endCoordinate)).isFalse();
    }

    @Test
    @DisplayName("블랙 폰은 가로로 이동할 수 없다")
    void isReachableByRuleCantStraight() {
        Coordinate startCoordinate = new Coordinate(6, 0);
        Coordinate endCoordinate = new Coordinate(6, 1);
        Pawn blackPawn = new BlackPawn(Color.BLACK);
        Pawn whitePawn = new WhitePawn(Color.WHITE);

        assertThat(blackPawn.isMovable(startCoordinate, endCoordinate)).isFalse();
        assertThat(whitePawn.isMovable(startCoordinate, endCoordinate)).isFalse();
    }

    @Test
    @DisplayName("블랙 폰이 화이트 폰을 우상단 대각 공격할 수 있다")
    void isReachableByRuleWhenAttackCaseNE() {
        Coordinate startCoordinate = new Coordinate(0, 0);
        Coordinate endCoordinate = new Coordinate(1, 1);

        Pawn blackPawn = new BlackPawn(Color.BLACK);
        Pawn whitePawn = new WhitePawn(Color.WHITE);

        assertThatCode(() -> blackPawn.isAttackable(startCoordinate, endCoordinate, whitePawn))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("화이트 폰이 블랙 폰을 좌상단 대각 공격할 수 있다")
    void isReachableByRuleWhenAttackCaseNW() {
        Coordinate startCoordinate = new Coordinate(0, 1);
        Coordinate endCoordinate = new Coordinate(1, 0);

        Pawn blackPawn = new BlackPawn(Color.BLACK);
        Pawn whitePawn = new WhitePawn(Color.WHITE);

        assertThatCode(() -> whitePawn.isAttackable(startCoordinate, endCoordinate, blackPawn))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("화이트 폰이 화이트 폰을 공격할 수 없다")
    void isReachableByRuleWhenAttackCaseNWSameTeam() {
        Coordinate startCoordinate = new Coordinate(0, 1);
        Coordinate endCoordinate = new Coordinate(1, 0);

        Pawn whitePawn = new BlackPawn(Color.WHITE);
        Pawn otherWhitePawn = new WhitePawn(Color.WHITE);

        assertThatCode(() -> whitePawn.isAttackable(startCoordinate, endCoordinate, otherWhitePawn))
                .doesNotThrowAnyException();
    }
}
