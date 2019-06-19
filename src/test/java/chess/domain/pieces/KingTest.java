package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KingTest {

    @Test
    void move() {
        King king = new King(ChessTeam.WHITE);
        assertEquals(new Direction(1, 1),
                king.move(new Point(1, 1), new Point(2, 2)));
    }

    @Test
    void attack() {
        King king = new King(ChessTeam.WHITE);
        assertEquals(new Direction(0, 1),
                king.attack(new Point(1, 1), new Point(1, 2)));
    }

    @Test
    void 이동_불가_테스트() {
        King king = new King(ChessTeam.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> king.move(new Point(1, 1), new Point(5, 3)));
    }
}