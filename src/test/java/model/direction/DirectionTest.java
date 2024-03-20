package model.direction;

import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class DirectionTest {

    @ParameterizedTest
    @EnumSource(Direction.class)
    @DisplayName("어떤 Position이 해당 Direction으로 움직였을 때, 체스판 영역 내의 위치인지를 판별한다.")
    void isMovedPositionAvailable(Direction direction) {
        assertTrue(direction.isMovedPositionAvailable(Position.of(4, 4)));
    }
}
