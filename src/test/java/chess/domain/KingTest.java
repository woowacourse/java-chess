package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KingTest {
    @Test
    void 생성() {
        assertDoesNotThrow(() -> new King(true));
    }

    @Test
    void 직각_이동() {
        Piece king = new King(true);
        List<Point> points = new ArrayList<>();
        points.add(new Point("a2"));
        assertThat(king.getCandidatePoints(new Point("a1"), new Point("a2"))).isEqualTo(points);
    }

    @Test
    void 이동_불가능() {
        Piece king = new King(true);
        assertThat(king.getCandidatePoints(new Point("a1"), new Point("h3")).size()).isEqualTo(0);
    }

    @Test
    void 점프() {
        Piece king = new King(true);
        assertThat(king.getCandidatePoints(new Point("a1"), new Point("a7")).size()).isEqualTo(0);
    }

    @Test
    void 두칸_이동_불가능() {
        Piece king = new King(true);
        assertThat(king.getCandidatePoints(new Point("a1"), new Point("a3")).size()).isEqualTo(0);
    }
}
