package chess.domain.piece;

import chess.domain.Coordinate;
import chess.domain.Position;
import chess.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chess.domain.utils.InputParser.position;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {

    Piece piece;
    Position base;

    @BeforeEach
    void setUp() {
        piece = new Knight(Team.BLACK);
        base = position("d4");
    }

    @Test
    void 일사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, position("e6")));
        assertTrue(piece.canMove(base, position("f5")));
    }

    @Test
    void 이사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, position("c6")));
        assertTrue(piece.canMove(base, position("b5")));
    }

    @Test
    void 삼사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, position("c2")));
        assertTrue(piece.canMove(base, position("b3")));
    }

    @Test
    void 사사분면_이동_여부_테스트() {
        assertTrue(piece.canMove(base, position("e2")));
        assertTrue(piece.canMove(base, position("f3")));
    }
}