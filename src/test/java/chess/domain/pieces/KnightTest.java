package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KnightTest {

    @Test
    void move() {
        Knight knight = new Knight(ChessTeam.WHITE);
        assertEquals(new Direction(2, 1),
                knight.move(Point.get(1, 1), Point.get(3, 2)));
    }

    @Test
    void attack() {
        Knight knight = new Knight(ChessTeam.WHITE);
        assertEquals(new Direction(2, 1),
                knight.attack(Point.get(1, 1), Point.get(3, 2)));
    }

    @Test
    void 이동_불가_테스트() {
        Knight knight = new Knight(ChessTeam.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> knight.move(Point.get(1, 1), Point.get(5, 3)));
    }
}