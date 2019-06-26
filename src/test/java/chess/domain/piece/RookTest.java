package chess.domain.piece;

import chess.domain.Coordinate;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.exceptions.InvalidDirectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static chess.domain.utils.InputParser.position;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {

    Piece piece;
    Position base;

    @BeforeEach
    void setUp() {
        piece = new Rook(Team.BLACK);
        base = position("b4");
    }

    @Test
    void 상하_이동_여부_테스트() {
        IntStream.rangeClosed(1, 8)
                .filter(i -> i != 4)
                .forEach(i -> assertTrue(piece.canMove(base, new Position(new Coordinate('b'), new Coordinate(i)))));
    }

    @Test
    void 좌우_이동_여부_테스트() {
        IntStream.rangeClosed(1, 8)
                .filter(i -> i != 2) // char b == int 2
                .forEach(i -> assertTrue(piece.canMove(base, new Position(new Coordinate(i), new Coordinate(4)))));
    }

    @Test
    void 대각선_이동_여부_테스트() {
        assertThrows(InvalidDirectionException.class, () -> piece.canMove(base, position("c5")));
    }
}