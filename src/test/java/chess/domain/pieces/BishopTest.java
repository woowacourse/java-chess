package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BishopTest {

    @Test
    void move_테스트() {
        Bishop bishop = new Bishop(ChessTeam.WHITE);
        assertEquals(new Direction(1, 1), bishop.move(Point.get(1, 1), Point.get(8, 8)));
    }


    @Test
    void attack_테스트() {
        Bishop bishop = new Bishop(ChessTeam.WHITE);
        assertEquals(new Direction(1, 1), bishop.attack(Point.get(1, 1), Point.get(8, 8)));
    }

    @Test
    void 이동_불가_테스트() {
        Bishop bishop = new Bishop(ChessTeam.WHITE);
        assertThrows(IllegalArgumentException.class, () -> bishop.move(Point.get(2, 2), Point.get(4, 2)));
    }

    @Test
    void 킹_아님_테스트() {
        Bishop bishop = new Bishop(ChessTeam.BLACK);
        assertFalse(bishop.isKing());
    }
}