package chess.domain.piece;

import chess.domain.Coordinate;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {

    AbstractPiece abstractPiece;
    Position base;

    @BeforeEach
    void setUp() {
        abstractPiece = new Rook(Team.BLACK);
        base = new Position(new Coordinate('b'), new Coordinate(4));
    }

    @Test
    void 상하_이동_여부_테스트() {
        IntStream.rangeClosed(1, 8)
                .filter(i -> i != 4)
                .forEach(i -> assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('b'), new Coordinate(i)))));
    }

    @Test
    void 좌우_이동_여부_테스트() {
        IntStream.rangeClosed(1, 8)
                .filter(i -> i != 2) // char b == int 2
                .forEach(i -> assertTrue(abstractPiece.canMove(base, new Position(new Coordinate(i), new Coordinate(4)))));
    }

    @Test
    void 대각선_이동_여부_테스트() {
        assertThrows(InvalidDirectionException.class, () -> {
            abstractPiece.canMove(base,
                    new Position(new Coordinate('c'), new Coordinate(5)));
        });
    }
}