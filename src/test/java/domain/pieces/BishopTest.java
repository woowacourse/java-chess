package domain.pieces;

import chess.domain.Point;
import chess.domain.PointFactory;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.Color;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BishopTest {

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new Bishop(Color.BLACK));
    }

    @Test
    void 정상_이동_Test() {
        Bishop bishop = new Bishop(Color.BLACK);
        Point source = PointFactory.of("d5");
        Point target = PointFactory.of("h1");
        List<Point> path = Arrays.asList(
                PointFactory.of("e4"),
                PointFactory.of("f3"),
                PointFactory.of("g2"));
        assertThat(path).isEqualTo(bishop.move(source, target));
    }

    @Test
    void 비정상_방향으로_이동_Test() {
        Bishop bishop = new Bishop(Color.BLACK);
        Point source = PointFactory.of("d5");
        Point target = PointFactory.of("a5");
        assertThrows(IllegalArgumentException.class, () -> bishop.move(source, target));
    }
}
