package domain.pieces;

import chess.domain.Point;
import chess.domain.PointFactory;
import chess.domain.pieces.Color;
import chess.domain.pieces.King;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KingTest {

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new King(Color.BLACK));
    }

    @Test
    void 정상_이동_Test() {
        King king = new King(Color.BLACK);
        Point source = PointFactory.of("e1");
        Point target = PointFactory.of("e2");
        assertThat(new ArrayList<>()).isEqualTo(king.move(source, target));
    }

    @Test
    void 비정상_이동_Test() {
        King king = new King(Color.BLACK);
        Point source = PointFactory.of("e1");
        Point target = PointFactory.of("e3");
        assertThrows(IllegalArgumentException.class, () -> king.move(source, target));
    }
}
