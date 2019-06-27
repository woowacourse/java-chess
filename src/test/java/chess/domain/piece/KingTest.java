package chess.domain.piece;

import chess.domain.board.Point;
import org.junit.jupiter.api.Test;

import static chess.domain.board.PlayerType.WHITE;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class KingTest {
    @Test
    void isMovable_AbsoluteGradient1_Distance2_True() {
        King king = new King(WHITE);
        assertThat(king.isMovable(Point.of(1, 1), Point.of(2, 2))).isTrue();
    }

    @Test
    void isMovable_AbsoluteGradient1_Distance4_False() {
        King king = new King(WHITE);
        assertThat(king.isMovable(Point.of(1, 1), Point.of(3, 3))).isFalse();
    }

    @Test
    void isMovable_AbsoluteGradient0_Distance1_True() {
        King king = new King(WHITE);
        assertThat(king.isMovable(Point.of(1, 1), Point.of(2, 1))).isTrue();
    }

    @Test
    void isMovable_AbsoluteGradientMax_Distance1_True() {
        King king = new King(WHITE);
        assertThat(king.isMovable(Point.of(1, 1), Point.of(1, 2))).isTrue();
    }

    @Test
    void isMovable_AbsoluteGradientMax_Distance2_False() {
        King king = new King(WHITE);
        assertThat(king.isMovable(Point.of(1, 1), Point.of(1, 3))).isFalse();
    }

    @Test
    void isMovable_AbsoluteGradient2_False() {
        King king = new King(WHITE);
        assertThat(king.isMovable(Point.of(1, 1), Point.of(2, 3))).isFalse();
    }
}
