package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {
    private Position position;

    @BeforeEach
    public void setUp() {
        position = new Position(2, 2);
    }

    @Test
    public void Position_잘_생성하는지_확인한다() {
        Position position = new Position(8, 8);
        assertThat(position).isExactlyInstanceOf(Position.class);
    }

    @Test
    public void 체스판을_벗어난_값이_입력됐을때_예외를_잘_던지는지_확인한다() {
        assertThatThrownBy(() -> {
            new Position(0, 0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void canMoveBackAndForth_Test() {
        assertThat(position.canMoveBackAndForth(new Position(2, 3))).isTrue();
        assertThat(position.canMoveBackAndForth(new Position(3, 2))).isFalse();
    }

    @Test
    public void canMoveSideToSide_Test() {
        assertThat(position.canMoveSideToSide(new Position(3, 2))).isTrue();
        assertThat(position.canMoveSideToSide(new Position(2, 3))).isFalse();
    }

    @Test
    public void canMoveDiagonally_Test() {
        assertThat(position.canMoveDiagonally(new Position(3, 3))).isTrue();
        assertThat(position.canMoveDiagonally(new Position(2, 3))).isFalse();
    }

    @Test
    public void getDistanceSquare_Test() {
        assertThat(position.getDistanceSquare(new Position(3, 3))).isEqualTo(2);
    }

    @Test
    public void isInStartingPosition_Test() {
        assertThat(position.isInStartingPosition()).isTrue();
    }
}