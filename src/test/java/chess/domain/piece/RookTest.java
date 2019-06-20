package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    void 가로_세칸_움직임() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(3, 3);

        Rook rook = new Rook(Team.BLACK);
        assertThat(rook.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 세로_한칸_움직임() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(1, 5);

        Rook rook = new Rook(Team.BLACK);
        assertThat(rook.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 공격_가능() {
        Rook rookA = new Rook(Team.BLACK);
        Rook rookB = new Rook(Team.BLACK);

        assertThat(rookA.canAttack(rookB)).isFalse();
    }

    @Test
    void 공격_불가능() {
        Rook rookA = new Rook(Team.BLACK);
        Rook rookB = new Rook(Team.WHITE);

        assertThat(rookA.canAttack(rookB)).isTrue();
    }
}