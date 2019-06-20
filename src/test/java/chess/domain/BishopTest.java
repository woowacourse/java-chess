package chess.domain;

import chess.domain.piece.Bishop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest {
    AbstractPiece abstractPiece;
    Position base;

    @BeforeEach
    void setUp() {
        abstractPiece = new Bishop(Team.BLACK);
        base = new Position(new Coordinate('d'), new Coordinate(4));
    }

    @Test
    void 우상향_대각선_이동_여부_테스트() {
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('h'), new Coordinate(8))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('g'), new Coordinate(7))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('f'), new Coordinate(6))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('e'), new Coordinate(5))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('c'), new Coordinate(3))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(2))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('a'), new Coordinate(1))));
    }

    @Test
    void 좌상향_대각선_이동_여부_테스트() {
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('a'), new Coordinate(7))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(6))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('c'), new Coordinate(5))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('e'), new Coordinate(3))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('f'), new Coordinate(2))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('g'), new Coordinate(1))));
    }

    @Test
    void 상하좌우_이동_여부_예외_테스트() {
        assertThrows(IllegalArgumentException.class, () -> {
            abstractPiece.canMove(base,
                    new Position(new Coordinate('e'), new Coordinate(4)));
        });
    }
}