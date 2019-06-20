package domain.direction;

import chess.domain.Position;
import chess.domain.direction.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {
    Direction direction;
    @Test
    void 위쪽방향_아래방향() {
        direction = new VerticalDirection();
        assertThat(direction.simulateUnitMove(Position.valueOf("a1"),false)).isEqualTo(Position.valueOf("a2"));
        assertThat(direction.simulateUnitMove(Position.valueOf("b2"),true)).isEqualTo(Position.valueOf("b1"));
    }

    @Test
    void 오른쪽방향_왼쪽방향() {
        direction = new HorizonDirection();
        assertThat(direction.simulateUnitMove(Position.valueOf("b2"),false)).isEqualTo(Position.valueOf("c2"));
        assertThat(direction.simulateUnitMove(Position.valueOf("b2"),true)).isEqualTo(Position.valueOf("a2"));
    }

    @Test
    void 오른대각선방향() {
        direction = new RithtDiagonalDirection();
        assertThat(direction.simulateUnitMove(Position.valueOf("b2"),false)).isEqualTo(Position.valueOf("c3"));
        assertThat(direction.simulateUnitMove(Position.valueOf("b2"),true)).isEqualTo(Position.valueOf("a1"));
    }

    @Test
    void 왼대각선방향() {
        direction = new LeftDiagonalDirection();
        assertThat(direction.simulateUnitMove(Position.valueOf("b2"),false)).isEqualTo(Position.valueOf("a3"));
        assertThat(direction.simulateUnitMove(Position.valueOf("b2"),true)).isEqualTo(Position.valueOf("c1"));
    }

    @Test
    void 위아래오른방향_나이트() {
        direction = new VerticalRightKnightDirection();
        assertThat(direction.simulateUnitMove(Position.valueOf("d4"),false)).isEqualTo(Position.valueOf("e6"));
        assertThat(direction.simulateUnitMove(Position.valueOf("d4"),true)).isEqualTo(Position.valueOf("c2"));
    }

    @Test
    void 위아래왼방향_나이트() {
        direction = new VerticalLeftKnightDirection();
        assertThat(direction.simulateUnitMove(Position.valueOf("d4"),false)).isEqualTo(Position.valueOf("c6"));
        assertThat(direction.simulateUnitMove(Position.valueOf("d4"),true)).isEqualTo(Position.valueOf("e2"));
    }

    @Test
    void 양옆오른방향_나이트() {
        direction = new HorizonRightKnightDirection();
        assertThat(direction.simulateUnitMove(Position.valueOf("d4"),false)).isEqualTo(Position.valueOf("f3"));
        assertThat(direction.simulateUnitMove(Position.valueOf("d4"),true)).isEqualTo(Position.valueOf("b5"));
    }

    @Test
    void 양옆왼방향_나이트() {
        direction = new HorizonLeftKnightDirection();
        assertThat(direction.simulateUnitMove(Position.valueOf("d4"),false)).isEqualTo(Position.valueOf("f5"));
        assertThat(direction.simulateUnitMove(Position.valueOf("d4"),true)).isEqualTo(Position.valueOf("b3"));
    }


}
