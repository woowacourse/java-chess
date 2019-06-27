package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @Test
    void 가로_이동() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(3, 3);

        Queen queen = new Queen(Team.BLACK);
        assertThat(queen.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 대각선_이동() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(4, 6);

        Queen queen = new Queen(Team.BLACK);
        assertThat(queen.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 세로_이동() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(1, 5);

        Queen queen = new Queen(Team.BLACK);
        assertThat(queen.isMovable(startSpot, endSpot)).isTrue();
    }
}