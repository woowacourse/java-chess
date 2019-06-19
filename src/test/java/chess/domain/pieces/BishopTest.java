package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopTest {

    @Test
    void move_테스트() {
        Bishop bishop = new Bishop(ChessTeam.WHITE);
        assertEquals(new Direction(1, 1), bishop.move(new Point(1, 1), new Point(8, 8)));
    }


    @Test
    void attack_테스트() {
        Bishop bishop = new Bishop(ChessTeam.WHITE);
        assertEquals(new Direction(1, 1), bishop.attack(new Point(1, 1), new Point(8, 8)));
    }

    @Test
    void 이동_불가_테스트() {
        Bishop bishop = new Bishop(ChessTeam.WHITE);
        assertThrows(IllegalArgumentException.class, () -> bishop.move(new Point(2, 2), new Point(4, 2)));
    }
}