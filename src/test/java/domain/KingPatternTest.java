package domain;

import chess.domain.movepatterns.KingPattern;
import chess.domain.movepatterns.MovePattern;
import chess.domain.Point;
import chess.domain.PointFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingPatternTest {

    MovePattern kingPattern = new KingPattern();

    @Test
    void 정상_이동_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("a2");
        assertTrue(kingPattern.canMove(source, target));
    }

    @Test
    void 비정상_이동_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("c1");
        assertFalse(kingPattern.canMove(source, target));
    }
}
