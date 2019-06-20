package chess.domain;

import chess.domain.piece.King;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

    AbstractPiece abstractPiece;
    Position base;

    @BeforeEach
    void setUp() {
        abstractPiece = new King(Team.BLACK);
        base = new Position(new Coordinate('b'), new Coordinate(2));
    }

    @Test
    void 상하좌우_이동_여부_테스트() {
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(3))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(1))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('a'), new Coordinate(2))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('c'), new Coordinate(2))));
    }

    @Test
    void 대각선_이동_여부_테스트() {
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('a'), new Coordinate(1))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('c'), new Coordinate(1))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('a'), new Coordinate(3))));
        assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('c'), new Coordinate(3))));
    }

    @Test
    void 상하거리_제한에_위반되는_경우_에외_테스트() {
        assertThrows(IllegalArgumentException.class, () -> {
            abstractPiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(4)));
        });
    }

    @Test
    void 대각선거리_제한에_위반되는_경우_에외_테스트() {
        assertThrows(IllegalArgumentException.class, () -> {
            abstractPiece.canMove(base, new Position(new Coordinate('a'), new Coordinate(4)));
        });
    }
}