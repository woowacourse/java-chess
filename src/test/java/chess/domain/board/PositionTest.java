package chess.domain.board;

import chess.domain.exceptions.InvalidMoveException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @DisplayName("좌표 동일성 비교")
    @Test
    void isEqualPosition_boolean() {
        Position position = basePosition();
        Position anotherPosition = Position.of('a', 1);

        assertEquals(position, anotherPosition);
    }

    @DisplayName("대칭 위치 계산")
    @Test
    void computeSymmetricPosition() {
        Position position = basePosition();
        Position targetPosition = Position.of('a', 8);

        Position computedPosition = position.computeSymmetricPosition();

        assertEquals(computedPosition, targetPosition);
    }

    @DisplayName("두 위치 사이의 가로 거리 비교")
    @Test
    void computeHorizontalPosition() {
        Position position = basePosition();
        Position anotherPosition = Position.of('d', 7);
        int predictedHorizontalDistance = 3;

        int distance = position.computeHorizontalDistance(anotherPosition);

        assertEquals(distance, predictedHorizontalDistance);
    }

    @DisplayName("두 위치 사이의 세로 거리 비교")
    @Test
    void name() {
        Position position = basePosition();
        Position anotherPosition = Position.of('d', 7);
        int predictedVerticalDistance = 6;

        int distance = position.computeVerticalDistance(anotherPosition);

        assertEquals(distance, predictedVerticalDistance);
    }

    @DisplayName("좌표 밖 선택시에 예외 처리")
    @Test
    void position_ExceptionThrown() {
        assertThatThrownBy(() -> Position.of('d', 9))
            .isInstanceOf(InvalidMoveException.class)
            .hasMessageContaining(Position.OUT_OF_BOUND_MESSAGE);
    }

    private Position basePosition() {
        return Position.of('a', 1);
    }
}