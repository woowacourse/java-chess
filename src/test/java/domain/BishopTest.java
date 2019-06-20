package domain;

import chess.domain.Bishop;
import chess.domain.Point;
import chess.domain.PointFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BishopTest {
    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new Bishop(0));
    }

    @Test
    void Bishop_대각선_경로_확인_Test() {
        List<Point> path = Arrays.asList(PointFactory.of("b2"), PointFactory.of("c3"));
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("d4");
        assertThat(new Bishop(1).makePath(source, target)).isEqualTo(path);
    }
}
