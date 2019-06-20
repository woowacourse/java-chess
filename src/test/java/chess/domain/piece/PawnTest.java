package chess.domain.piece;

import chess.domain.board.Point;
import org.junit.jupiter.api.Test;

import static chess.domain.board.PlayerType.WHITE;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class PawnTest {
    @Test
    void isMovable_xDistanceOver1_whenFirst_False() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 1), Point.of(4, 1))).isFalse();
    }

    @Test
    void isMovable_yDistanceOve2_whenFirst_False() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 1), Point.of(1, 5))).isFalse();
    }

    @Test
    void isMovable_xDistance1_whenFirst_True() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 1), Point.of(2, 1))).isTrue();
    }

    @Test
    void isMovable_yDistance2_whenFirst_True() {
        Pawn pawn = new Pawn(WHITE);
        assertThat(pawn.isMovable(Point.of(1, 1), Point.of(1, 3))).isTrue();
    }

    @Test
    void black() {
        // TODO: 2019-06-20 Create BLACK version!
    }
}
