package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class QueenTest {
    @Test
    void 생성() {
        assertDoesNotThrow(() -> new Queen(Team.WHITE));
    }
    @Test
    void 대각선_이동() {
        Piece queen = new Queen(Team.WHITE);
        List<Point> points = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            points.add(new Point((char)('a' + i), (char)('1'+ i)));
        }
        assertThat(queen.getCandidatePoints(new Point("a1"), new Point("c3"))).isEqualTo(points);
    }

    @Test
    void 직각_이동() {
        Piece queen = new Queen(Team.WHITE);
        List<Point> points = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            points.add(new Point('a', (char)('1'+ i)));
        }
        assertThat(queen.getCandidatePoints(new Point("a1"), new Point("a3"))).isEqualTo(points);
    }


    @Test
    void 이동_불가능() {
        Piece queen = new Queen(Team.WHITE);
        assertThat(queen.getCandidatePoints(new Point("a1"), new Point("h3")).size()).isEqualTo(0);
    }
}
