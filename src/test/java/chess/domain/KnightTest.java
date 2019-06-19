package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {

    Piece piece;
    Position base;

    @BeforeEach
    void setUp() {
        piece = new Knight(Team.BLACK);
        base = new Position(new Coordinate('d'), new Coordinate(4));
    }

    @Test
    void 일사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, new Position(new Coordinate('e'), new Coordinate(6))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('f'), new Coordinate(5))));
    }

    @Test
    void 이사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, new Position(new Coordinate('c'), new Coordinate(6))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(5))));
    }

    @Test
    void 삼사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, new Position(new Coordinate('c'), new Coordinate(2))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(3))));
    }

    @Test
    void 사사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, new Position(new Coordinate('e'), new Coordinate(2))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('f'), new Coordinate(3))));
    }
}