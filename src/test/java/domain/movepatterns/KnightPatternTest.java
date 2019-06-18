package domain.movepatterns;

import chess.domain.Point;
import chess.domain.PointFactory;
import chess.domain.movepatterns.KnightPattern;
import chess.domain.movepatterns.MovePattern;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightPatternTest {

    MovePattern knightPattern = new KnightPattern();

    @Test
    void 정상_이동_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("b3");
        assertTrue(knightPattern.canMove(source, target));
    }

    @Test
    void 비정상_이동_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("b2");
        assertFalse(knightPattern.canMove(source, target));
    }
}
