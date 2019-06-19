package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueenTest {
    @Test
    void move() {
        Queen queen = new Queen(ChessTeam.WHITE);
        assertEquals(new Direction(1, 1),
                queen.move(new Point(1, 1), new Point(5, 5)));
    }

    @Test
    void attack() {
        Queen queen = new Queen(ChessTeam.WHITE);
        assertEquals(new Direction(0, 1),
                queen.attack(new Point(1, 1), new Point(1, 7)));
    }

    @Test
    void 이동_불가_테스트() {
        Queen queen = new Queen(ChessTeam.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> queen.move(new Point(1, 1), new Point(5, 3)));
    }
}