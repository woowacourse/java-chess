package chess.domain.pieces;

import chess.domain.Point;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BishopTest {
    @Test
    void 생성() {
        assertDoesNotThrow(() -> new Bishop(Team.WHITE));
    }

    @Test
    void 이동() {
        Piece bishop = new Bishop(Team.WHITE);
        List<Point> points = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            points.add(new Point((char) ('a' + i), (char) ('1' + i)));
        }
        assertThat(bishop.move(new Point("a1"), new Point("c3"))).isEqualTo(points);
    }

    @Test
    void 이동_불가능() {
        Piece bishop = new Bishop(Team.WHITE);
        assertThat(bishop.move(new Point("a1"), new Point("a2")).size()).isEqualTo(0);
    }
}
