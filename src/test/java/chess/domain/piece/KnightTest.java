package chess.domain.piece;

import chess.domain.board.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class KnightTest {
    @Test
    void isMovable_AbsoluteGradient1_False() {
        Knight knight = new Knight();
        assertThat(knight.isMovable(Point.of(1, 1), Point.of(2, 2))).isFalse();
    }

    @Test
    void isMovable_AbsoluteGradient0_False() {
        Knight knight = new Knight();
        assertThat(knight.isMovable(Point.of(1, 1), Point.of(2, 1))).isFalse();
    }

    @Test
    void isMovable_AbsoluteGradientMax_False() {
        Knight knight = new Knight();
        assertThat(knight.isMovable(Point.of(1, 1), Point.of(1, 2))).isFalse();
    }

    @Test
    void isMovable_AbsoluteGradient2_Distance3_True() {
        Knight knight = new Knight();
        assertThat(knight.isMovable(Point.of(1, 1), Point.of(2, 3))).isTrue();
    }

    @Test
    void isMovable_AbsoluteGradientHalf_Distance3_True() {
        Knight knight = new Knight();
        assertThat(knight.isMovable(Point.of(1, 1), Point.of(3, 2))).isTrue();
    }

    @Test
    void isMovable_AbsoluteGradient2_Distance6_False() {
        Knight knight = new Knight();
        assertThat(knight.isMovable(Point.of(1, 1), Point.of(3, 5))).isFalse();
    }

    @Test
    void isMovable_AbsoluteGradientHalf_Distance6_False() {
        Knight knight = new Knight();
        assertThat(knight.isMovable(Point.of(1, 1), Point.of(5, 3))).isFalse();
    }


}
