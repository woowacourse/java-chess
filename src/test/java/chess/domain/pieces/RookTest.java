package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @Test
    void move() {
        Rook rook = new Rook(ChessTeam.WHITE);
        assertThat(rook.move(new Point(2, 2), new Point(8, 2))).isEqualTo(new Direction(1, 0));
    }

    @Test
    void attack() {
        Rook rook = new Rook(ChessTeam.WHITE);
        assertThat(rook.attack(new Point(2, 2), new Point(8, 2))).isEqualTo(new Direction(1, 0));
    }
}