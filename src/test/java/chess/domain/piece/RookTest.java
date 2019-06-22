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
    void 대각선_이동_불가() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 4);

        Rook rook = new Rook(Team.BLACK);
        assertThat(rook.isMovable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 같은_팀_검사() {
        Rook rookA = new Rook(Team.BLACK);
        Rook rookB = new Rook(Team.BLACK);

        assertThat(rookA.isSameTeam(rookB)).isTrue();
    }

    @Test
    void 다른_팀_검사() {
        Rook rookA = new Rook(Team.BLACK);
        Rook rookB = new Rook(Team.WHITE);

        assertThat(rookA.isSameTeam(rookB)).isFalse();
    }
}