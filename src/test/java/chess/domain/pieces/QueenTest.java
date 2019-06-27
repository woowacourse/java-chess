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
                queen.move(Point.get(1, 1), Point.get(5, 5)));
    }

    @Test
    void attack() {
        Queen queen = new Queen(ChessTeam.WHITE);
        assertEquals(new Direction(0, 1),
                queen.attack(Point.get(1, 1), Point.get(1, 7)));
    }

    @Test
    void 이동_불가_테스트() {
        Queen queen = new Queen(ChessTeam.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> queen.move(Point.get(1, 1), Point.get(5, 3)));
    }
}