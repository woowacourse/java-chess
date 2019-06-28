package chess.domain.pieces;

import chess.domain.Point;
import chess.domain.PointFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueenTest {

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new Queen(Color.BLACK));
    }

    @Test
    void 정상_이동_Test() {
        Queen queen = new Queen(Color.BLACK);
        Point source = PointFactory.of("d1");
        Point target = PointFactory.of("d5");
        List<Point> path = Arrays.asList(
                PointFactory.of("d2"),
                PointFactory.of("d3"),
                PointFactory.of("d4"));
        assertThat(path).isEqualTo(queen.action(source, target, false));
    }

    @Test
    void 비정상_방향으로_이동_Test() {
        Queen queen = new Queen(Color.BLACK);
        Point source = PointFactory.of("d4");
        Point target = PointFactory.of("e6");
        assertThrows(IllegalArgumentException.class, () -> queen.action(source, target, false));
    }
}
