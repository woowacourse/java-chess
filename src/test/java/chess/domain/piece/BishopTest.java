package chess.domain.piece;

import chess.domain.board.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class BishopTest {
    @Test
    void isMovable_AbsoluteGradient1_True() {
        Bishop bishop = new Bishop();
        assertThat(bishop.isMovable(Point.of(1,1),Point.of(2,2))).isTrue();
    }
    @Test
    void isMovable_AbsoluteGradient0_False() {
        Bishop bishop = new Bishop();
        assertThat(bishop.isMovable(Point.of(1,1),Point.of(2,1))).isFalse();
    }
    @Test
    void isMovable_AbsoluteGradientMax_False() {
        Bishop bishop = new Bishop();
        assertThat(bishop.isMovable(Point.of(1,1),Point.of(1,2))).isFalse();
    }
    @Test
    void isMovable_AbsoluteGradient2_False() {
        Bishop bishop = new Bishop();
        assertThat(bishop.isMovable(Point.of(1,1),Point.of(2,3))).isFalse();
    }
}
