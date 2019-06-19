package chess.domain.piece;

import chess.domain.board.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class QueenTest {
    @Test
    void isMovable_AbsoluteGradient1_True() {
        Queen queen = new Queen();
        assertThat(queen.isMovable(Point.of(1,1),Point.of(2,2))).isTrue();
    }
    @Test
    void isMovable_AbsoluteGradient0_True() {
        Queen queen = new Queen();
        assertThat(queen.isMovable(Point.of(1,1),Point.of(2,1))).isTrue();
    }
    @Test
    void isMovable_AbsoluteGradientMax_True() {
        Queen queen = new Queen();
        assertThat(queen.isMovable(Point.of(1,1),Point.of(1,2))).isTrue();
    }
    @Test
    void isMovable_AbsoluteGradient2_False() {
        Queen queen = new Queen();
        assertThat(queen.isMovable(Point.of(1,1),Point.of(2,3))).isFalse();
    }
}