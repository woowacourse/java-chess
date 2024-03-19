package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UpDirectionTest {
    
    @Test
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void canReach() {
        UpDirection leftDirection = new UpDirection(8);
        Position from = new Position(1, 8);
        Position to = new Position(3, 8);
        assertThat(leftDirection.canReach(from, to, List.of())).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 더이상 이동이 불가능한 경우 거짓을 반환한다.")
    void canNotReachWhenNowIsBoundary() {
        UpDirection direction = new UpDirection(8);
        Position from = new Position(8, 1);
        Position to = new Position(7, 1);
        assertThat(direction.canReach(from, to, List.of())).isFalse();
    }

    @Test
    @DisplayName("도착위치 중간에 장애물이 있을 경우 거짓을 반환한다.")
    void canNotReachWhenObstacleExist() {
        UpDirection leftDirection = new UpDirection(8);
        Position from = new Position(1, 3);
        Position to = new Position(8, 3);
        assertThat(leftDirection.canReach(from, to, List.of(new Position(4, 3)))).isFalse();
    }

    @Test
    @DisplayName("이동할 수 있는 방향의 개수를 모두 소진함에도 불구하고 도달하지 못할 경우 거짓을 반환한다.")
    void canNotReachWhenOverMoveCount() {
        UpDirection leftDirection = new UpDirection(1);
        Position from = new Position(1, 8);
        Position to = new Position(1, 6);
        assertThat(leftDirection.canReach(from, to, List.of())).isFalse();
    }
}

