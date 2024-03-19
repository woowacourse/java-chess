package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LeftDirectionTest {

    @Test
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void canReach() {
        LeftDirection leftDirection = new LeftDirection(8);
        Position from = new Position(1, 8);
        Position to = new Position(1, 6);
        assertThat(leftDirection.canReach(from, to, List.of())).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 더이상 이동이 불가능한 경우 거짓을 반환한다.")
    void canNotReachWhenNowIsBoundary() {
        LeftDirection direction = new LeftDirection(8);
        Position from = new Position(1, 1);
        Position to = new Position(7, 1);
        assertThat(direction.canReach(from, to, List.of())).isFalse();
    }

    @Test
    @DisplayName("도착위치 중간에 장애물이 있을 경우 거짓을 반환한다.")
    void canNotReachWhenObstacleExist() {
        LeftDirection leftDirection = new LeftDirection(8);
        Position from = new Position(1, 8);
        Position to = new Position(1, 6);
        assertThat(leftDirection.canReach(from, to, List.of(new Position(1, 7)))).isFalse();
    }

    @Test
    @DisplayName("이동할 수 있는 방향의 개수를 모두 소진함에도 불구하고 도달하지 못할 경우 거짓을 반환한다.")
    void canNotReachWhenOverMoveCount() {
        LeftDirection leftDirection = new LeftDirection(1);
        Position from = new Position(1, 8);
        Position to = new Position(1, 6);
        assertThat(leftDirection.canReach(from, to, List.of())).isFalse();
    }
}
