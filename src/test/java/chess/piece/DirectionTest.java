package chess.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Point;
import chess.domain.piece.Direction;

public class DirectionTest {
    @DisplayName("Direction 확인")
    @Test
    void findDirection() {
        Direction direction = Direction.findDirection(Point.of("a1"), Point.of("h8"));
        Assertions.assertEquals(Direction.NORTH_EAST, direction);

        Direction direction2 = Direction.findDirection(Point.valueOf(1, 4), Point.valueOf(0, 4));
        Assertions.assertEquals(Direction.NORTH, direction2);
    }

    @DisplayName("옳지 않은 Direction 확인")
    @Test
    void checkWrongDirection() {
        assertThatThrownBy(() -> Direction.findDirection(Point.valueOf(1, 4), Point.valueOf(3, 5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("기물이 움직일 수 없는 방향입니다.");

        assertThatThrownBy(() -> Direction.findDirection(Point.valueOf(1, 4), Point.valueOf(1, 4)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("기물이 움직이지 않습니다.");
    }
}
