package chess.piece;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {
    @DisplayName("Direction 확인")
    @Test
    void findDirection() {
        Direction direction1 = Direction.createDirection(1, 1);
        Direction direction2 = Direction.createDirection(-1, -1);
        Direction direction3 = Direction.createDirection(-1, 1);
        Direction direction4 = Direction.createDirection(1, -1);

        assertEquals(direction1, Direction.SOUTH_EAST);
        assertEquals(direction2, Direction.NORTH_WEST);
        assertEquals(direction3, Direction.NORTH_EAST);
        assertEquals(direction4, Direction.SOUTH_WEST);
    }
}
