package domain.movepatterns;

import chess.domain.Point;
import chess.domain.PointFactory;
import chess.domain.movepatterns.QueenPattern;
import chess.domain.movepatterns.MovePattern;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenPatternTest {

    MovePattern queenPattern = new QueenPattern();

    @Test
    void 직선_정상_이동_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("a2");
        assertTrue(queenPattern.canMove(source, target));
    }

    @Test
    void 대각선_정상_이동_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("c3");
        assertTrue(queenPattern.canMove(source, target));
    }

    @Test
    void 비정상_이동_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("b3");
        assertFalse(queenPattern.canMove(source, target));
    }
}
