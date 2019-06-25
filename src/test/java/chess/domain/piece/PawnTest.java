package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    void 블랙_폰_움직_1칸_시작점() {
        Spot startSpot = Spot.valueOf(1, 1);
        Spot endSpot = Spot.valueOf(1, 2);

        Pawn pawn = new Pawn(Team.BLACK);

        assertThat(pawn.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 블랙_폰_움직_2칸_시작점() {
        Spot startSpot = Spot.valueOf(1, 1);
        Spot endSpot = Spot.valueOf(1, 3);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 블랙_폰_움직_1칸_시작점_아닐때() {
        Spot startSpot = Spot.valueOf(2, 2);
        Spot endSpot = Spot.valueOf(2, 3);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 블랙_폰_움직_2칸_시작점_아닐때() {
        Spot startSpot = Spot.valueOf(2, 2);
        Spot endSpot = Spot.valueOf(2, 4);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 블랙_폰_공격_가능_테스트() {
        Spot startSpot = Spot.valueOf(2, 3);
        Spot endSpot = Spot.valueOf(3, 4);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isAttackable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 블랙_폰_공격_불가능_테스트() {
        Spot startSpot = Spot.valueOf(2, 3);
        Spot endSpot = Spot.valueOf(2, 4);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isAttackable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 화이트_폰_움직_1칸_시작점() {
        Spot startSpot = Spot.valueOf(1, 6);
        Spot endSpot = Spot.valueOf(1, 5);

        Pawn pawn = new Pawn(Team.WHITE);

        assertThat(pawn.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 화이트_폰_움직_2칸_시작점() {
        Spot startSpot = Spot.valueOf(1, 6);
        Spot endSpot = Spot.valueOf(1, 4);

        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 화이트_폰_움직_1칸_시작점_아닐때() {
        Spot startSpot = Spot.valueOf(2, 5);
        Spot endSpot = Spot.valueOf(2, 4);

        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 화이트_폰_움직_2칸_시작점_아닐때() {
        Spot startSpot = Spot.valueOf(2, 5);
        Spot endSpot = Spot.valueOf(2, 3);

        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isMovable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 화이트_폰_공격_가능_테스트() {
        Spot startSpot = Spot.valueOf(2, 5);
        Spot endSpot = Spot.valueOf(3, 4);

        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isAttackable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 화이트_폰_공격_불가능_테스트() {
        Spot startSpot = Spot.valueOf(2, 5);
        Spot endSpot = Spot.valueOf(1,5);

        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isAttackable(startSpot, endSpot)).isFalse();
    }

}