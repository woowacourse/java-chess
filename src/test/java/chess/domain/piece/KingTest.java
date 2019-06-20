package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @Test
    void 두칸이상_떨어진_경우() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 5);

        King king = new King(Team.BLANK);
        assertThat(king.isMovable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 가로_한칸_이동() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 3);

        King king = new King(Team.BLANK);
        assertThat(king.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 세로_한칸_이동() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(1, 4);

        King king = new King(Team.BLANK);
        assertThat(king.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 대각선_한칸_이동() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 4);

        King king = new King(Team.BLANK);
        assertThat(king.isMovable(startSpot, endSpot)).isTrue();
    }
}