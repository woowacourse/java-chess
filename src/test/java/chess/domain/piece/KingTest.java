package chess.domain.piece;

import chess.domain.Piece;
import chess.domain.Coordinate;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;
import chess.domain.exceptions.InvalidDistanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {

    Piece piece;
    Position base;

    @BeforeEach
    void setUp() {
        piece = new King(Team.BLACK);
        base = new Position(new Coordinate('b'), new Coordinate(2));
    }

    @Test
    void 상하좌우_이동_여부_테스트() {
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(3))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(1))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('a'), new Coordinate(2))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('c'), new Coordinate(2))));
    }

    @Test
    void 대각선_이동_여부_테스트() {
        assertTrue(piece.canMove(base, new Position(new Coordinate('a'), new Coordinate(1))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('c'), new Coordinate(1))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('a'), new Coordinate(3))));
        assertTrue(piece.canMove(base, new Position(new Coordinate('c'), new Coordinate(3))));
    }

    @Test
    void 상하거리_제한에_위반되는_경우_에외_테스트() {
        assertThrows(InvalidDistanceException.class, () -> {
            piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(4)));
        });
    }

    @Test
    void 대각선거리_제한에_위반되는_경우_에외_테스트() {
        assertThrows(InvalidDirectionException.class, () -> {
            piece.canMove(base, new Position(new Coordinate('a'), new Coordinate(4)));
        });
    }
}