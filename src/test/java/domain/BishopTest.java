package domain;

import chess.domain.pieces.Bishop;
import chess.domain.Point;
import chess.domain.PointFactory;
import chess.domain.pieces.Color;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BishopTest {
    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new Bishop(Color.WHITE));
    }

    @Test
    void Bishop_대각선_경로_확인_Test() {
        List<Point> path = Arrays.asList(PointFactory.of("b2"), PointFactory.of("c3"));
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("d4");
        assertThat(new Bishop(Color.WHITE).makePath(source, target)).isEqualTo(path);
    }
}
