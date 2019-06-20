package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    void 움직임_테스트() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 5);

        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 대각선_움직임() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 4);

        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.isMovable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 가로_움직임() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 3);

        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.isMovable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 세로_움직임() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(1, 4);

        Knight knight = new Knight(Team.BLACK);
        assertThat(knight.isMovable(startSpot, endSpot)).isFalse();
    }

}