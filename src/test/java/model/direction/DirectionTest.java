package model.direction;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class DirectionTest {

    @ParameterizedTest
    @EnumSource(Direction.class)
    @DisplayName("어떤 Position이 해당 Direction으로 움직였을 때, 체스판 영역 내의 위치인지를 판별한다.")
    void isMovedPositionAvailable(Direction direction) {
        assertTrue(direction.isMovedPositionAvailable(Position.of(4, 4)));
    }

    @Test
    @DisplayName("특정 Position이 해당 Direction으로 움직였을 때, 변경된 위치의 Position을 반환한다.")
    void getMovedPosition() {
        Position initialPosition = Position.of(4,4);
        assertAll(
                () -> {
                    assertEquals(Position.of(4,5), Direction.N.getMovedPosition(initialPosition));
                    assertEquals(Position.of(4,3), Direction.S.getMovedPosition(initialPosition));
                    assertEquals(Position.of(5,4), Direction.E.getMovedPosition(initialPosition));
                    assertEquals(Position.of(3,4), Direction.W.getMovedPosition(initialPosition));
                    assertEquals(Position.of(5,5), Direction.NE.getMovedPosition(initialPosition));
                    assertEquals(Position.of(3,5), Direction.NW.getMovedPosition(initialPosition));
                    assertEquals(Position.of(5,3), Direction.SE.getMovedPosition(initialPosition));
                    assertEquals(Position.of(3,3), Direction.SW.getMovedPosition(initialPosition));
                }
        );
    }
}
