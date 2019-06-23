package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class RookTest {
    @Test
    void 생성() {
        assertDoesNotThrow(() -> new Rook(Team.WHITE));
    }

    @Test
    void 이동() {
        Rook rook = new Rook(Team.WHITE);
        List<Point> points = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            points.add(new Point('a', (char)('1'+ i)));
        }
        assertThat(rook.move(new Point("a1"), new Point("a5"))).isEqualTo(points);
    }

    @Test
    void 이동_불가능() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.move(new Point("a1"), new Point("b2")).size()).isEqualTo(0);
    }
}
