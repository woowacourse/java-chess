package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {
    @Test
    void 생성() {
        Position position = Position.getPosition(1, 8);
        assertThat(position).isEqualTo(Position.getPosition(1, 8));
    }

    @Test
    void 이동() {
        Position start = Position.getPosition(1, 1);
        assertThat(start.move(Direction.TOP)).isEqualTo(Position.getPosition(1, 2));
    }

    @Test
    void 왼쪽_위_방향으로_이동_가능() {
        Position start = Position.getPosition(5, 5);
        assertTrue(start.canMove(Direction.LEFT_TOP));
    }

    @Test
    void 왼쪽_위_방향으로_이동_불가능() {
        Position start = Position.getPosition(1, 8);
        assertFalse(start.canMove(Direction.LEFT_TOP));
    }

    @Test
    void 아래_방향으로_이동_가능() {
        Position start = Position.getPosition(5, 5);
        assertTrue(start.canMove(Direction.BOTTOM));
    }

    @Test
    void 아래_방향으로_이동_불가능() {
        Position start = Position.getPosition(3, 1);
        assertFalse(start.canMove(Direction.BOTTOM));
    }

    @Test
    void 오른쪽_오른쪽_아래_방향으로_이동_가능() {
        Position start = Position.getPosition(5, 5);
        assertTrue(start.canMove(Direction.RIGHT_RIGHT_BOTTOM));
    }

    @Test
    void 오른쪽_오른쪽_아래_방향으로_이동_불가능() {
        Position start = Position.getPosition(7, 2);
        assertFalse(start.canMove(Direction.RIGHT_RIGHT_BOTTOM));
    }
}