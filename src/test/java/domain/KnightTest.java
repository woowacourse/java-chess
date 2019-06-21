package domain;

import chess.domain.pieces.Color;
import chess.domain.pieces.Knight;
import chess.domain.Point;
import chess.domain.PointFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new Knight(Color.WHITE));
    }

    @Test
    void Knight_경로_확인_Test() {
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("b3");
        assertTrue(new Knight(Color.WHITE).makePath(source, target).isEmpty());
    }
}
