package domain.movepatterns;

import chess.domain.Point;
import chess.domain.PointFactory;
import chess.domain.movepatterns.MovePattern;
import chess.domain.movepatterns.PawnPattern;
import chess.domain.pieces.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnPatternTest {

    MovePattern pawnPattern = new PawnPattern(Color.WHITE);

    @Test
    void 한_칸_정상이동_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("a2");
        assertTrue(pawnPattern.canMove(source, target));
    }

    @Test
    void 두_칸_정상이동_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("a3");
        assertTrue(pawnPattern.canMove(source, target));
    }

    @Test
    void 비정상이동_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("b2");
        assertFalse(pawnPattern.canMove(source, target));
    }

}
