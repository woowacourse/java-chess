package domain.movepatterns;

import chess.domain.Point;
import chess.domain.PointFactory;
import chess.domain.movepatterns.BishopPattern;
import chess.domain.movepatterns.MovePattern;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopPatternTest {

    MovePattern bishopPattern = new BishopPattern();

    @Test
    void 정상_이동_Test() {
        Point source = PointFactory.of("d5");
        Point target = PointFactory.of("a2");
        assertTrue(bishopPattern.canMove(source, target));
    }

    @Test
    void 비정상_이동_Test() {
        Point source = PointFactory.of("d5");
        Point target = PointFactory.of("e5");
        assertFalse(bishopPattern.canMove(source, target));
    }
}
