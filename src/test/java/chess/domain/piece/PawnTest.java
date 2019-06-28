package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @Test
    void 폰_공격() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 4);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isAttackable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 폰_뒤로_공격() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 2);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isAttackable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 폰_앞으로_한칸_공격() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(1, 4);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isAttackable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 폰_대각선_두칸_공격() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 5);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isAttackable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 폰_한칸_움직임() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(1, 4);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 폰_두칸_움직임() {
        Spot startSpot = Spot.valueOf(1, 1);
        Spot endSpot = Spot.valueOf(1, 3);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 폰_두칸_움직임_불가() {
        Spot startSpot = Spot.valueOf(1, 2);
        Spot endSpot = Spot.valueOf(1, 4);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 폰_가로_움직임() {
        Spot startSpot = Spot.valueOf(1, 2);
        Spot endSpot = Spot.valueOf(2, 2);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 폰_대각선_움직임() {
        Spot startSpot = Spot.valueOf(1, 2);
        Spot endSpot = Spot.valueOf(2, 3);

        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovable(startSpot, endSpot)).isFalse();
    }
}