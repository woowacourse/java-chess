package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {

    Piece piece;
    Position base;

    @BeforeEach
    void setUp() {
        piece = new Rook(Team.BLACK);
        base = new Position(new Coordinate('b'), new Coordinate(4));
    }

    @Test
    void 상하_이동_여부_테스트() {
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(7))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(6))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(5))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(3))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(2))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(1))));
    }

    @Test
    void 좌우_이동_여부_테스트() {
        assertTrue(piece.canMove(base, new Position(new Coordinate('a'), new Coordinate(4))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('c'), new Coordinate(4))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('d'), new Coordinate(4))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('e'), new Coordinate(4))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('f'), new Coordinate(4))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('g'), new Coordinate(4))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('h'), new Coordinate(4))));
    }

    @Test
    void 대각선_이동_여부_테스트() {
        assertThrows(IllegalArgumentException.class, () -> {
            piece.canMove(new Position(new Coordinate('a'), new Coordinate(1)),
                    new Position(new Coordinate('b'), new Coordinate(2)));
        });
    }
}