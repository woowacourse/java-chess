package chess.domain.pieces;

import chess.domain.Point;
import chess.domain.PointFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KnightTest {

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new Knight(Color.BLACK));
    }

    @Test
    void 정상_이동_Test() {
        Knight knight = new Knight(Color.BLACK);
        Point source = PointFactory.of("d4");
        Point target = PointFactory.of("c2");
        assertThat(new ArrayList<>()).isEqualTo(knight.action(source, target, false));
    }

    @Test
    void 비정상_이동_Test() {
        Knight knight = new Knight(Color.BLACK);
        Point source = PointFactory.of("d4");
        Point target = PointFactory.of("d5");
        assertThrows(IllegalArgumentException.class, () -> knight.action(source, target, false));
    }
}
