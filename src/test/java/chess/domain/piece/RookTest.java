package chess.domain.piece;

import chess.domain.board.Point;
import org.junit.jupiter.api.Test;

import static chess.domain.board.PlayerType.WHITE;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class RookTest {
    @Test
    void isMovable_AbsoluteGradient0_True() {
        Rook rook = new Rook(WHITE);
        assertThat(rook.isMovable(Point.of(1,1),Point.of(2,1))).isTrue();
    }
    @Test
    void isMovable_AbsoluteGradientMax_True() {
        Rook rook = new Rook(WHITE);
        assertThat(rook.isMovable(Point.of(1,1),Point.of(1,2))).isTrue();
    }
    @Test
    void isMovable_AbsoluteGradient2_False() {
        Rook rook = new Rook(WHITE);
        assertThat(rook.isMovable(Point.of(1,1),Point.of(2,3))).isFalse();
    }
}
