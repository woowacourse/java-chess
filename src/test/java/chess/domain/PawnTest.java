package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {

    Piece piece;
    @BeforeEach
    void setUp() {
        piece = new Pawn(Team.BLACK);
    }

    @Test
    void 상하좌우_이동_여부_테스트() {
        Position base = new Position(new Coordinate('b'), new Coordinate(2));
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(3))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(1))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('a'), new Coordinate(2))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('c'), new Coordinate(2))));
    }

    @Test
    void 대각선_이동_여부_테스트() {
        assertFalse(piece.canMove(new Position(new Coordinate('a'), new Coordinate(1)),
                new Position(new Coordinate('b'), new Coordinate(2))));
    }
}
