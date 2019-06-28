package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    void 대각선_움직임() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 4);

        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.isMovable(startSpot, endSpot)).isTrue();
    }

    @Test
    void 가로_움직임() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(2, 3);

        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.isMovable(startSpot, endSpot)).isFalse();
    }

    @Test
    void 세로_움직임() {
        Spot startSpot = Spot.valueOf(1, 3);
        Spot endSpot = Spot.valueOf(1, 4);

        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.isMovable(startSpot, endSpot)).isFalse();
    }

}