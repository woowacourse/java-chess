package chess.domain.pieces;

import chess.domain.Point;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RookTest {

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new Rook(Color.BLACK));
    }

    @Test
    void 정상_이동_Test() {
        Rook rook = new Rook(Color.BLACK);
        Point source = PointFactory.of("d5");
        Point target = PointFactory.of("d1");
        List<Point> path = Arrays.asList(
                PointFactory.of("d4"),
                PointFactory.of("d3"),
                PointFactory.of("d2"));
        assertThat(path).isEqualTo(rook.action(source, target, false));
    }

    @Test
    void 비정상_방향으로_이동_Test() {
        Rook rook = new Rook(Color.BLACK);
        Point source = PointFactory.of("d5");
        Point target = PointFactory.of("f7");
        assertThrows(IllegalArgumentException.class, () -> rook.action(source, target, false));
    }
}
