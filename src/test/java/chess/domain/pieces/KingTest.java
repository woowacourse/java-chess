package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    @Test
    void move() {
        King king = new King(ChessTeam.WHITE);
        assertEquals(new Direction(1, 1),
                king.move(Point.get(1, 1), Point.get(2, 2)));
    }

    @Test
    void attack() {
        King king = new King(ChessTeam.WHITE);
        assertEquals(new Direction(0, 1),
                king.attack(Point.get(1, 1), Point.get(1, 2)));
    }

    @Test
    void 이동_불가_테스트() {
        King king = new King(ChessTeam.WHITE);
        assertThrows(IllegalArgumentException.class,
                () -> king.move(Point.get(1, 1), Point.get(5, 3)));
    }

    @Test
    void 킹_확인_테스트() {
        King king = new King(ChessTeam.BLACK);
        assertTrue(king.isKing());
    }
}