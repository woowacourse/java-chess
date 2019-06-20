package domain;

import chess.domain.Point;
import chess.domain.PointFactory;
import chess.domain.Rook;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RookTest {

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new Rook(0));
    }

    @Test
    void Rook_직선_경로_확인_Test() {
        List<Point> path = Arrays.asList(PointFactory.of("a2"), PointFactory.of("a3"));
        Point source = PointFactory.of("a1");
        Point target = PointFactory.of("a4");
        assertThat(new Rook(1).makePath(source, target)).isEqualTo(path);
    }
}
