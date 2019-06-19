package domain;

import chess.domain.King;
import chess.domain.Point;
import chess.domain.PointFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTest {

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new King(0));
    }

    @Test
    void King_경로_확인_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("a2");
        assertTrue(new King(1).makePath(source,target).isEmpty());
    }
}
