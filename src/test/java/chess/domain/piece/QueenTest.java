package chess.domain.piece;

import chess.domain.Coordinate;
import chess.domain.Position;
import chess.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {

    AbstractPiece abstractPiece;
    Position base;

    @BeforeEach
    void setUp() {
        abstractPiece = new Queen(Team.BLACK);
        base = new Position(new Coordinate('d'), new Coordinate(4));
    }

    @Test
    void 상하_이동_여부_테스트() {
        IntStream.rangeClosed(1, 8)
                .filter(i -> i != 4)
                .forEach(i -> assertTrue(abstractPiece.canMove(base, new Position(new Coordinate('d'), new Coordinate(i)))));
    }

    @Test
    void 좌우_이동_여부_테스트() {
        IntStream.rangeClosed(1, 8)
                .filter(i -> i != 4)
                .forEach(i -> assertTrue(abstractPiece.canMove(base, new Position(new Coordinate(i), new Coordinate(4)))));
    }

    @Test
    void 우상향_대각선_이동_여부_테스트() {
        IntStream.rangeClosed(1, 8)
                .filter(i -> i != 4)
                .forEach(i -> assertTrue(abstractPiece.canMove(base, new Position(new Coordinate(i), new Coordinate(i)))));
    }

    @Test
    void 좌상향_대각선_이동_여부_테스트() {
        IntStream.range(1, 8)
                .filter(i -> i != 4)
                .forEach(i -> assertTrue(abstractPiece.canMove(base, new Position(new Coordinate(i), new Coordinate(8 - i)))));
    }
}