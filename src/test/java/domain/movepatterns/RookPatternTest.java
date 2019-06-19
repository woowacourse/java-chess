package domain.movepatterns;

import chess.domain.Point;
import chess.domain.PointFactory;
import chess.domain.movepatterns.MovePattern;
import chess.domain.movepatterns.RookPattern;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookPatternTest {

    MovePattern rookPattern= new RookPattern();

    @Test
    void 정상_이동_Test(){
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("a2");
        assertTrue(rookPattern.canMove(source, target));
    }

    @Test
    void 비정상_이동_Test(){
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("b2");
        assertFalse(rookPattern.canMove(source, target));
    }

}
